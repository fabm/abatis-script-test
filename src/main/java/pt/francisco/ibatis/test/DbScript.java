package pt.francisco.ibatis.test;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class DbScript {
    public static void main(String args[]) throws IOException, SQLException {

        File file = new File("config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream(file), args[0]);

        System.out.println("Database environment:" + args[0]);
        File sqlFile = new File(args[1]);
        System.out.println("Executing file:" + sqlFile.getAbsolutePath());

        ScriptRunner scriptRunner = new ScriptRunner(sqlSessionFactory.openSession().getConnection());
        scriptRunner.runScript(new FileReader(sqlFile));
    }
}
