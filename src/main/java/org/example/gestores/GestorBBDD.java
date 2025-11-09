package org.example.gestores;

import org.example.Juego;

import java.sql.*;

public class GestorBBDD {

    private Connection conn;

    public GestorBBDD(Connection conn) {
        this.conn = conn;
    }

    public void insertarJuego(Juego j) {
        String sql = "INSERT INTO Juego_PlayStation2 (ID_Game, Title, Developer, Publisher, Europe__PAL, Japan, North_America) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            if(j.getTitle().equalsIgnoreCase("God of War")){
                System.out.println("No se agregar God of War ");
                conn.rollback();
                return;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, j.getID_Game());
            ps.setString(2, j.getTitle());
            ps.setString(3, j.getDeveloper());
            ps.setString(4, j.getPublisher());
            ps.setString(5, j.getEurope_PAL());
            ps.setString(6, j.getJapan());
            ps.setString(7, j.getNorth_America());

            ps.executeUpdate();
            conn.commit();
            ps.close();

            System.out.println("Juego insertado: " + j.getTitle());



    } catch (SQLException e) {


            System.out.println("Eror al insertarlo");
            throw new RuntimeException(e);

        }
        }



    public void listarJuegos() {
        String sql = "SELECT * FROM Juego_PlayStation2";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("ID_Game") + " | " +
                                rs.getString("Title") + " | " +
                                rs.getString("Developer") + " | " +
                                rs.getString("Publisher") + " | " +
                                rs.getString("Europe__PAL") + " | " +
                                rs.getString("Japan") + " | " +
                                rs.getString("North_America")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
