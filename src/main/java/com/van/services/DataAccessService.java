package com.van.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vly on 1/23/2016.
 */
@Service
public class DataAccessService {

    @Autowired
    DataSource ds;

    @Autowired
    @Resource(name = "dataSourceOne")
    DataSource ds1;

    @Autowired
    @Resource(name = "dataSourceTwo")
    DataSource ds2;

    @PostConstruct
    /**
     * Initialize the two DBs by creating a table t in each DB and insert 3 rows each.
     * Datasource One will have IDs 0, 1, and 2. Datasource Two will have IDs 10, 11, and 12.
     * So the same query against the table t, depending on the data source, will return
     * different values.
     */
    public void init() {
        JdbcTemplate jt = new JdbcTemplate(ds1);
        jt.execute("create table t (id integer)");

        for (int i = 0; i < 3; i++) {
            jt.update(String.format("insert into t values(%d)", i));
        }

        jt = new JdbcTemplate(ds2);
        jt.execute("create table t (id integer)");
        for (int i = 10; i < 13; i++) {
            jt.update(String.format("insert into t values(%d)", i));
        }
    }

    /**
     * Return a list of IDs from the one table t from the data source specified
     *
     * @param dataSourceSelector 0, 1, or 2, indicating the data source to query:
     *                           <ul>
     *                              <li>0 - default (should be same as datasource 1)</li>
     *                              <li>1 - datasource 1</li>
     *                              <li>2 - datasource 2</li>
     *                           </ul>
     *
     * @return the list of rows found in table t
     */
    public List getData(int dataSourceSelector) {
        DataSource ds = this.ds;
        switch (dataSourceSelector) {
            case 1:
                ds = ds1;
                break;
            case 2:
                ds = ds2;
                break;
            default:
        }
        JdbcTemplate jt = new JdbcTemplate(ds);
        return jt.query("select * from t", (resultSet, i) -> {
            List<Object> row = new LinkedList<>();
            row.add(resultSet.getInt(1));
            return row;
        });
    }
}
