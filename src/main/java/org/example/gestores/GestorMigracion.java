package org.example.gestores;

import java.sql.*;

public class GestorMigracion {



    public void crearTablas(Connection connOr, Connection connDes) throws SQLException {
        DatabaseMetaData meta = connOr.getMetaData();
        ResultSet tablas = meta.getTables(null, null, "Juego_GameCube", null);

        Statement stmDes = connDes.createStatement();

        while(tablas.next()){
            String nomTablaDes = "Juego_Playstation2";
            ResultSet columnas = meta.getColumns(null, null, "Juego_GameCube", null);

            StringBuilder strBuild = new StringBuilder("CREATE TABLE IF NOT EXISTS " + nomTablaDes + " (");

            boolean primer = true;
            while(columnas.next()){
                if (!primer) strBuild.append(",");
                primer = false;

                strBuild.append(columnas.getString("COLUMN_NAME")).append(" ").append(columnas.getString("TYPE_NAME"));

            }
            strBuild.append(");");

            stmDes.execute(strBuild.toString());
            System.out.println("La tabla se ha creado con el nombre: " + nomTablaDes);
        }
    }
    public void enviarDatos(Connection connOr,Connection connDes) throws SQLException {

        Statement stmOr = connOr.createStatement();
        Statement stmDes = connDes.createStatement();

        ResultSet rs = stmOr.executeQuery("SELECT * FROM Juego_GameCube");
        String sqlInsert = "INSERT INTO Juego_PlayStation2 VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connDes.prepareStatement(sqlInsert);


        while(rs.next()){
            ps.setInt(1, rs.getInt("ID_Game"));
            ps.setString(2, rs.getString("Title"));
            ps.setString(3, rs.getString("Developer"));
            ps.setString(4, rs.getString("Publisher"));
            ps.setString(5, rs.getString("Europe__PAL"));
            ps.setString(6, rs.getString("Japan"));
            ps.setString(7, rs.getString("North_America"));

            ps.executeUpdate();

            System.out.println("Los datos han sido copiados a playstation 2");

            ps.close();
            rs.close();
            stmOr.close();
        }




    }
}
