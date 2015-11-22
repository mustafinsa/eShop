package com.mustafinsa.eshop.Model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectorDB {
    public static DataSource getDataSource() {
        InitialContext ctx = null;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ctx != null) {
                    ctx.close();
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }
}
