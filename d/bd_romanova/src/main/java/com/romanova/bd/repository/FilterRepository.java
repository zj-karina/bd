package com.romanova.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class FilterRepository {
    private static final String SELECT_SQL = "select * from TABLE";
    private static final String FILTER_ONE_FIELD_SQL = "select * from TABLE where FIELD1 COMP1 ?";
    private static final String FILTER_TWO_FIELDS_SQL =
            "select * from TABLE where FIELD1 COMP1 ? LOG_SIGN1 FIELD2 COMP2 ?";
    private static final String FILTER_THREE_FIELDS_SQL =
            "select * from TABLE where FIELD1 COMP1 ? LOG_SIGN1 FIELD2 COMP2 ? LOG_SIGN2 FIELD3 COMP3 ?";

    private final JdbcTemplate template;

    public List<Map<String, Object>> getAllObjects(String table){
        String sql = SELECT_SQL.replace("TABLE", table);
        return template.queryForList(sql);
    }

    public List<Map<String, Object>> getFilteredObjectsOneFilter(String table, String f1, String c1, String v1){
        String sql = FILTER_ONE_FIELD_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1);
        return template.queryForList(sql, v1);
    }

    public List<Map<String, Object>> getFilteredObjectsTwoFilters(String table,
                                                                  String f1, String c1, String v1, String f2, String c2, String v2, String ls1){
        String sql = FILTER_TWO_FIELDS_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1)
                .replace("FIELD2", f2)
                .replace("COMP2", c2)
                .replace("LOG_SIGN1", ls1);
        return template.queryForList(sql, v1, v2);
    }

    public List<Map<String, Object>> getFilteredObjectsThreeFilters(String table,
                                                                    String f1, String c1, String v1, String f2, String c2, String v2, String f3, String c3, String v3, String ls1, String ls2){
        String sql = FILTER_THREE_FIELDS_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1)
                .replace("FIELD2", f2)
                .replace("COMP2", c2)
                .replace("FIELD3", f3)
                .replace("COMP3", c3)
                .replace("LOG_SIGN1", ls1)
                .replace("LOG_SIGN2", ls2);
        return template.queryForList(sql, v1, v2, v3);
    }
}
