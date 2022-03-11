package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Traitement {

	private Statement st;
	public ResultSet res;
	String sql;
	PreparedStatement pstmt;

	public Traitement() {

	}

	public int inscription(Utilisateur a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();

			sql = "insert into utilisateur(nom,prenom,email,motDePasse) values(?,?,?,?) ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setString(1, a.getNom());
			pstmt.setString(2, a.getPrenom());
			pstmt.setString(3, a.getEmail());
			pstmt.setString(4, a.getMotDePasse());
			n = pstmt.executeUpdate();
			res=st.executeQuery("select id from utilisateur order by id desc limit 1");
			res.next();
			sql = "insert into favoris(favoris.id_utilisateur) values("+res.getInt(1)+")";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}

	public int ajouterFavoris(Utilisateur a,Livre livre) {
	
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select * from etre_constitue_de where id="+livre.getId()+" and id_favoris="+a.getId_favoris()+"");

			//System.out.println("favoris");
			if(res.next()) {
				sql = "delete from etre_constitue_de where id=? and id_favoris=? ";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setInt(1, livre.getId());
				pstmt.setInt(2, a.getId_favoris());

				pstmt.executeUpdate();
				return 0;
			}else {
				sql = "insert into etre_constitue_de values(?,?) ";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setInt(1, livre.getId());
				pstmt.setInt(2, a.getId_favoris());
				 pstmt.executeUpdate();
				return 1;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return 0;
	}
	
	public int ajouterNote(Utilisateur a,Livre livre,int note) {
		
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select * from note where id_livre="+livre.getId()+" and id_utilisateur="+a.getId()+"");

			if(res.next()) {
				if(res.getInt(2)!=note) {
				sql = "update note set nombreEtoile=? where id_livre=? and id_utilisateur=?  ";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setInt(1, note);
				pstmt.setInt(2, livre.getId());
				pstmt.setInt(3, a.getId());

				pstmt.executeUpdate();
				return note;
				}else return note;
			}else {
				sql = "insert into note(nombreEtoile,id_livre,id_utilisateur) values(?,?,?) ";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setInt(1, note);
				pstmt.setInt(2, livre.getId());
				pstmt.setInt(3, a.getId());

				 pstmt.executeUpdate();
				return note;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return 0;
	}
	
	public int getUserNote(Utilisateur a,Livre livre) {
		int note=0;
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select * from note where id_livre="+livre.getId()+" and id_utilisateur="+a.getId()+"");

			if(res.next()) {
				note=res.getInt(2);
				return note;
				}else return note;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return 0;
	}
	
	public Livre getlivre(Livre livre) {
		int note=0;
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select pdf from livre where id="+livre.getId());

			if(res.next()) {
				livre.setpDF(res.getBytes(1));
				return livre;
				}else return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return null;
	}
	public Livre getAudio(Livre livre) {
		int note=0;
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select audio from livre where id="+livre.getId());

			if(res.next()) {
				livre.setAudio(res.getBytes(1));

				return livre;
				}else return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return null;
	}
	
	public boolean isFavoris(Utilisateur a,Livre livre) {
		
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select * from etre_constitue_de where id="+livre.getId()+" and id_favoris="+a.getId_favoris()+"");

			if(res.next()) {
				return true;
			}else {
				return false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return false;
	}

	public int ahouterLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			sql = "select id from auteur where nom='" + a.getAuteur().toLowerCase() + "'";
			/*
			 * pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			 * pstmt.setString(1, "'" + a.getAuteur().toLowerCase() + "'");
			 */

			int numauteur = 0;
			res = null;
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery(sql);

			if (res.next()) {

				numauteur = res.getInt(1);
			} else {
				sql = "insert into auteur(nom) values(?)";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setString(1, a.getAuteur().toLowerCase());
				pstmt.executeUpdate();
				sql = "select id from auteur where nom='" + a.getAuteur().toLowerCase() + "'";
				/*
				 * pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				 * pstmt.setString(1, "'" + a.getAuteur().toLowerCase() + "'");
				 */

				res = st.executeQuery(sql);
				if (res.next()) {

					numauteur = res.getInt("id");

				}
			}
			sql = "select id from genre where genre='" + a.getGenre() + "'";

			int numGenre = 0;
			res = null;
			res = st.executeQuery(sql);
			if (res.next()) {
				numGenre = res.getInt(1);
			} else {
				sql = "insert into genre(genre) values(?)";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setString(1, a.getGenre().toLowerCase());
				pstmt.executeUpdate();
				sql = "select id from genre where genre='" + a.getGenre() + "'";
				
				res = null;
				res = st.executeQuery(sql);
				if (res.next())
					numGenre = res.getInt(1);
			}

			sql = "insert into livre(titre,dateDeParition,nombrePage,pdf,audio,id_genre,id_auteur,couverture,extrait) values(?,?,?,?,?,?,?,?,?) ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setString(1, a.getTitre());
			pstmt.setDate(2, a.getDateDeParition());
			pstmt.setInt(3, a.getNombreDePage());

			// System.out.println(a.getpDF().exists());
			// File file=new File(a.getpDF().toString());
			pstmt.setBytes(4, a.getpDF());
			// file=new File(a.getAudio().toString());
			pstmt.setBytes(5, a.getAudio());

			pstmt.setInt(6, numGenre);
			pstmt.setInt(7, numauteur);
			pstmt.setBytes(8, a.getCouverture());
			pstmt.setString(9, a.getExtrait());
			n = pstmt.executeUpdate();
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	public int updateinformationsLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			sql = "select id from auteur where nom='" + a.getAuteur().toLowerCase() + "'";
			/*
			 * pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			 * pstmt.setString(1, "'" + a.getAuteur().toLowerCase() + "'");
			 */

			int numauteur = 0;
			res = null;
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery(sql);

			if (res.next()) {

				numauteur = res.getInt(1);
			} else {
				sql = "insert into auteur(nom) values(?)";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setString(1, a.getAuteur().toLowerCase());
				pstmt.executeUpdate();
				sql = "select id from auteur where nom='" + a.getAuteur().toLowerCase() + "'";
				/*
				 * pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				 * pstmt.setString(1, "'" + a.getAuteur().toLowerCase() + "'");
				 */

				res = st.executeQuery(sql);
				if (res.next()) {

					System.out.println("auteur" +res.getInt(1));
					numauteur = res.getInt(1);

				}
			}
			sql = "select id from genre where genre='" + a.getGenre() + "'";

			int numGenre = 0;
			res = null;
			res = st.executeQuery(sql);
			if (res.next()) {
				numGenre = res.getInt(1);
			} else {
				sql = "insert into genre(genre) values(?)";
				pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
				pstmt.setString(1, a.getGenre().toLowerCase());
				pstmt.executeUpdate();
				sql = "select id from genre where genre='" + a.getGenre() + "'";
				
				res = null;
				res = st.executeQuery(sql);
				if (res.next())
					numGenre = res.getInt(1);
			}

			sql = "update livre set titre='"+a.getTitre()+"',"
					+ "dateDeParition='"+a.getDateDeParition()+"',nombrePage="+a.getNombreDePage()+",id_genre="+numGenre+",id_auteur="+numauteur+",extrait='"+a.getExtrait()+"' where id="+a.getId()+" ";
			st =  (Statement) (ConnexionDB.cnx).createStatement();
			
			n = st.executeUpdate(sql);
			deleteEmptyAuteur();
			deleteEmptyGenre();
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	
	public int updatecouvertureLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			

			sql = "update livre set couverture=? where id=? ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setBytes(1, a.getCouverture());
			pstmt.setInt(2, a.getId());
			pstmt.executeUpdate();
			
			n = pstmt.executeUpdate(sql);
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	public int updateContenuLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			

			sql = "update livre set pdf=? where id=? ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setBytes(1, a.getpDF());
			pstmt.setInt(2, a.getId());
			pstmt.executeUpdate();
			
			n = pstmt.executeUpdate(sql);
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	public int updateAudioLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			

			sql = "update livre set audio=? where id=? ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setBytes(1, a.getAudio());
			pstmt.setInt(2, a.getId());
			pstmt.executeUpdate();
			
			n = pstmt.executeUpdate(sql);
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	public int deleteLivreLivre(Livre a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			
			st=(Statement) ConnexionDB.cnx.createStatement();
			st.executeUpdate("delete from note where id_livre="+a.getId());
			st.executeUpdate("delete from etre_constitue_de where id="+a.getId());
			sql = "delete from livre where id="+a.getId();
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			n = pstmt.executeUpdate(sql);
			deleteEmptyAuteur();
			deleteEmptyGenre();
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}

	public int updateUser(Utilisateur a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			

			sql = "update utilisateur set nom='"+a.getNom()+"', prenom='"+a.getPrenom()+"', email='"+a.getEmail()+"', motDePasse='"+a.getMotDePasse()+"' where id="+a.getId()+"";
			st =  (Statement) (ConnexionDB.cnx).createStatement();
			
			n = st.executeUpdate(sql);
			
			
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	
	public int updateAdmin(Utilisateur a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			

			sql = "update admin set nom='"+a.getNom()+"', prenom='"+a.getPrenom()+"', email='"+a.getEmail()+"', motDePasse='"+a.getMotDePasse()+"' where id="+a.getId()+"";
			st =  (Statement) (ConnexionDB.cnx).createStatement();
			
			n = st.executeUpdate(sql);
			
			
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	
	
	public int deleteUser(Utilisateur a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			
			st=(Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select id from favoris where id_utilisateur="+a.getId());
			if(res.next())
			{
				int id=res.getInt(1);
				st.executeUpdate("delete from etre_constitue_de where id_favoris="+id);
				st.executeUpdate("delete from favoris where id_utilisateur="+a.getId());
				st.executeUpdate("delete from note where id_utilisateur="+a.getId());
			}
			sql = "delete from utilisateur where id="+a.getId();
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			
			
			pstmt.executeUpdate();
			
			n = pstmt.executeUpdate(sql);
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	
	public Utilisateur connectAdmin(Utilisateur user) {
		try {
			ConnexionDB.getconnection();
			Utilisateur u = new Utilisateur();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			sql = "select * from admin where email='" + user.getEmail() + "' and motDePasse='" + user.getMotDePasse()
					+ "'";
			res = null;

			res = st.executeQuery(sql);
			if (res.next()) {
				u.setNom(res.getString(2));
				u.setPrenom(res.getString(3));
				u.setEmail(res.getString(4));
				u.setMotDePasse(res.getString(5));
				return u;
			}
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public Utilisateur connectUtilisateur(Utilisateur user) {
		try {
			ConnexionDB.getconnection();
			Utilisateur u = new Utilisateur();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			sql = "select * from utilisateur,favoris where utilisateur.id=favoris.id_utilisateur and email='" + user.getEmail() + "' and motDePasse='"
					+ user.getMotDePasse() + "'";
			res = null;

			res = st.executeQuery(sql);
			if (res.next()) {
				u.setId(res.getInt(1));
				u.setNom(res.getString(2));
				u.setPrenom(res.getString(3));
				u.setEmail(res.getString(4));
				u.setMotDePasse(res.getString(5));
				u.setId_favoris(res.getInt(6));

				return u;
			}
			ConnexionDB.cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;

	}
	

	/*
	 * public Abonne getAbonne(int id) { Abonne a=null; try {
	 * ConnexionDB.getconnection(); st = (Statement)
	 * (ConnexionDB.cnx).createStatement(); ResultSet
	 * res=st.executeQuery("SELECT * FROM `abonne` where id="+id); if(res.next()) {
	 * a=new Abonne();
	 * 
	 * a.setId(res.getInt(1)); a.setNom(res.getString(2));
	 * a.setPrenom(res.getString(3)); a.setTele(res.getString(4));
	 * a.setPaiement(res.getDouble(5)); Statement st1=(Statement)
	 * (ConnexionDB.cnx).createStatement(); ResultSet
	 * res1=st1.executeQuery("SELECT * FROM `date` where idAbonne="
	 * +id+" order by idDate desc");
	 * 
	 * ArrayList<Date> dates=new ArrayList<Date>();
	 * 
	 * ArrayList<Integer> indexDate=new ArrayList<Integer>(); while(res1.next()) {
	 * dates.add(res1.getDate(2)); indexDate.add(res1.getInt(1));
	 * 
	 * } a.setIndexDate(indexDate); a.setDate(dates); } ConnexionDB.cnx.close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); try { ConnexionDB.cnx.close(); } catch (SQLException e1)
	 * { // TODO Auto-generated catch block e1.printStackTrace(); } } return a; }
	 * 
	 * 
	 * public void payer(int id) { sql =
	 * "insert into date(date,idAbonne) values(?,?)";
	 * 
	 * java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	 * try { ConnexionDB.getconnection(); st = (Statement)
	 * (ConnexionDB.cnx).createStatement(); pstmt = (PreparedStatement)
	 * (ConnexionDB.cnx).prepareStatement(sql); pstmt.setDate(1, sqlDate);
	 * 
	 * pstmt.setInt(2, id); pstmt.executeUpdate(); ConnexionDB.cnx.close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); try { ConnexionDB.cnx.close(); } catch (SQLException e1)
	 * { // TODO Auto-generated catch block e1.printStackTrace(); } }
	 * 
	 * }
	 */
	public ArrayList<Livre> getFavorisLivres(Utilisateur user) {

		ArrayList<Livre> list = new ArrayList<Livre>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select livre.id,titre,dateDeParition,nombrePage,couverture,genre,nom,avg(note.nombreEtoile) "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur,favoris,etre_constitue_de where auteur.Id=livre.Id_auteur"
					+ " and genre.id=livre.id_genre and favoris.id=etre_constitue_de.id_favoris and favoris.id_utilisateur="+user.getId()+
					" and etre_constitue_de.id=livre.id group by livre.id");
			while (res.next()) {
				Livre livre = new Livre();
				livre.setId(res.getInt(1));
				livre.setTitre(res.getString(2));
				livre.setDateDeParition(res.getDate(3));
				livre.setNombreDePage(res.getInt(4));
				livre.setCouverture(res.getBytes(5));
				livre.setGenre(res.getString(6));
				livre.setAuteur(res.getString(7));
				 livre.setNombreEtoiles(res.getInt(8));
				livre.setItem();
				
				list.add(livre);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Livre> getLivres() {

		ArrayList<Livre> list = new ArrayList<Livre>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select livre.id,titre,dateDeParition,nombrePage,couverture,genre,nom,avg(note.nombreEtoile) "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur where auteur.Id=livre.Id_auteur and genre.id=livre.id_genre group by livre.id");
			while (res.next()) {
				Livre livre = new Livre();
				livre.setId(res.getInt(1));
				livre.setTitre(res.getString(2));
				livre.setDateDeParition(res.getDate(3));
				livre.setNombreDePage(res.getInt(4));
				livre.setCouverture(res.getBytes(5));
				livre.setGenre(res.getString(6));
				livre.setAuteur(res.getString(7));
				 livre.setNombreEtoiles(res.getInt(8));
				livre.setItem();
				
				list.add(livre);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Livre> getStatistiqueByLivres(int top) {

		ArrayList<Livre> list = new ArrayList<Livre>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select titre,avg(note.nombreEtoile) as avgetoile "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur where auteur.Id=livre.Id_auteur and genre.id=livre.id_genre group by livre.id  order by avgetoile desc limit "+top);
			while (res.next()) {
				Livre livre = new Livre();
				
				livre.setTitre(res.getString(1));
				
				 livre.setNombreEtoiles(res.getInt(2));
			
				
				list.add(livre);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Livre> getStatistiqueByAuteur(int top) {

		ArrayList<Livre> list = new ArrayList<Livre>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select nom,avg(note.nombreEtoile) as avgetoile "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur where auteur.Id=livre.Id_auteur and genre.id=livre.id_genre group by nom  order by avgetoile desc limit "+top);
			while (res.next()) {
				Livre livre = new Livre();
				
				livre.setAuteur(res.getString(1));
				
				 livre.setNombreEtoiles(res.getInt(2));
			
				
				list.add(livre);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Livre> getStatistiqueByGenre(int top) {

		ArrayList<Livre> list = new ArrayList<Livre>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select genre,avg(note.nombreEtoile) as avgetoile "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur where auteur.Id=livre.Id_auteur and genre.id=livre.id_genre group by genre  order by avgetoile desc limit "+top);
			while (res.next()) {
				Livre livre = new Livre();
				
				livre.setGenre(res.getString(1));
				
				 livre.setNombreEtoiles(res.getInt(2));
			
				
				list.add(livre);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Utilisateur> getUsers() {

		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select * from utilisateur");
			
			while (res.next()) {
				Utilisateur user=new Utilisateur();
				user.setId(res.getInt(1));
				user.setNom(res.getString(2));
				user.setPrenom(res.getString(3));
				user.setEmail(res.getString(4));
				user.setMotDePasse(res.getString(5));
			
				
				list.add(user);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	
	public Utilisateur getAdmin() {

		ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select * from admin");
			
			Utilisateur user=new Utilisateur();
			if (res.next()) {
				
				user.setId(res.getInt(1));
				user.setNom(res.getString(2));
				user.setPrenom(res.getString(3));
				user.setEmail(res.getString(4));
				user.setMotDePasse(res.getString(5));
			
				
				return user	;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	public Livre getLivre(Livre search) {

		Livre livre = new Livre();

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res = st.executeQuery("select livre.id,titre,dateDeParition,nombrePage,couverture,genre,nom,avg(note.nombreEtoile),extrait "
					+ "from livre left join note on livre.id=note.id_livre,genre,auteur where "
					+ "auteur.Id=livre.Id_auteur and genre.id=livre.id_genre and livre.id="+search.getId());
			if(res.next()) {
				
				livre.setId(res.getInt(1));
				livre.setTitre(res.getString(2));
				livre.setDateDeParition(res.getDate(3));
				livre.setNombreDePage(res.getInt(4));
				livre.setCouverture(res.getBytes(5));
				livre.setGenre(res.getString(6));
				livre.setAuteur(res.getString(7));
				 livre.setNombreEtoiles(res.getInt(8));
				livre.setItem();
				livre.setExtrait(res.getString(9));
				
				
			}
			ConnexionDB.cnx.close();
			return livre;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ConnexionDB.cnx.close();
				
			} catch (SQLException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public void deleteEmptyAuteur() throws SQLException {
		st=(Statement) ConnexionDB.cnx.createStatement();
		st.executeUpdate("delete from auteur where id not in(select auteur.id from livre where livre.Id_auteur=auteur.Id)");
		}
	
public void deleteEmptyGenre() throws SQLException {
	st=(Statement) ConnexionDB.cnx.createStatement();
	st.executeUpdate("delete from genre where id not in(select genre.id from livre where livre.Id_genre=genre.Id)");
	}
}