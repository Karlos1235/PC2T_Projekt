package projekt;

import java.sql.*;
import java.util.TreeSet;

public class Databáze {
	private Connection connection;
	
	public boolean getDBConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:mojeDatabáze.db");
                vytvorTabulku();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void vytvorTabulku() {
        String sql = "CREATE TABLE IF NOT EXISTS knihy (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "název TEXT NOT NULL," +
                     "autoři TEXT NOT NULL," +
                     "rokVydání INTEGER," +
                     "dostupnost BOOLEAN," +
                     "žánr TEXT NOT NULL," +
                     "úroveňRočníku INTEGER" +
                     ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void pridejKnihu(TreeSet<Knihy> knihy) {
        vytvorTabulku();
    	String sql = "INSERT INTO knihy (název, autoři, rokVydání, dostupná, žánr, úroveňRočníku) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            for (Knihy kniha : knihy) {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, kniha.getNázev());
                String[] autoři = kniha.getAutoři();
                String autořiString = String.join(",", autoři);
                pstmt.setString(2, autořiString);
                pstmt.setInt(3, kniha.getRokVydání());
                pstmt.setBoolean(4, kniha.isDostupná());
                if (kniha instanceof Román) {
                    Román román = (Román) kniha;
                    pstmt.setString(5, román.getŽánr());
                    pstmt.setInt(6, 0);
                } else if (kniha instanceof Učebnice){
                	Učebnice učebnice = (Učebnice) kniha;
                	pstmt.setString(5, "0");
                	pstmt.setInt(6, učebnice.getÚroveňRočníku());
                }
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public TreeSet<Knihy> nactiKnihu() {
        TreeSet<Knihy> knihy = new TreeSet<>();
        String sql = "SELECT * FROM knihy";
        try {
        	for(Knihy kniha: knihy){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String název = rs.getString("název");
                String autořiString = rs.getString("autoři");
                String[] autoři = autořiString.split(",");
                int rokVydání = rs.getInt("rokVydáni");
                boolean dostupná = rs.getBoolean("dostupná");
                String žánr = rs.getString("žánr");
                int úroveňRočniku = rs.getInt("úroveňRočníku");
                kniha = new Knihy(název,autoři, rokVydání, dostupná);
                if(úroveňRočniku == 0) {
                	kniha = new Román(název, autoři, rokVydání, dostupná, žánr);
                } else {
                	kniha = new Učebnice(název, autoři, rokVydání, dostupná, úroveňRočniku);
                }
                
                knihy.add(kniha);
            }
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return knihy;
    }

}
