package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Quartiere;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getProvider(){
		final String sql="SELECT DISTINCT Provider "
				+ "FROM nyc_wifi_hotspot_locations h ";
		List<String> result= new LinkedList<String>();
		Connection conn= DBConnect.getConnection();
		try {
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				result.add(rs.getString("Provider"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
		}
		return result;
	}
	
	public List<Quartiere> getQuartieri(String provider){
		final String sql="SELECT distinct city,AVG(Latitude) AS lat,AVG(Longitude) AS lon, COUNT(*) AS tot "
				+ "FROM nyc_wifi_hotspot_locations h "
				+ "WHERE Provider=? "
				+ "GROUP BY city "
				+ "ORDER BY city ";
		List<Quartiere> result= new LinkedList<>();
		Connection conn=DBConnect.getConnection();
		try {
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				LatLng pos = new LatLng(rs.getDouble("lat"),rs.getDouble("lon"));
				Quartiere q = new Quartiere(rs.getString("city"),
											pos,
											rs.getInt("tot"));
				result.add(q);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
		}
		return result;
	}
}
