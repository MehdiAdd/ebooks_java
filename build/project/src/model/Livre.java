package model;



import java.sql.Date;

public class Livre {

	@SuppressWarnings("unused")
	private int id;
	@SuppressWarnings("unused")
	private String titre;
	@SuppressWarnings("unused")
	private Date dateDeParition;
	@SuppressWarnings("unused")
	private int nombreDePage;
	@SuppressWarnings("unused")
	private byte[] couverture;
	public byte[] getCouverture() {
		return couverture;
	}
	public void setCouverture(byte[] couverture) {
		this.couverture = couverture;
	}
	@SuppressWarnings("unused")
	private byte[] pDF;
	@SuppressWarnings("unused")
	private byte[] audio=null;
	@SuppressWarnings("unused")
	private String genre;
	@SuppressWarnings("unused")
	private String auteur;
	@SuppressWarnings("unused")
	private int idGenre;
	public int getIdGenre() {
		return idGenre;
	}
	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}
	public int getIdAuteur() {
		return idAuteur;
	}
	public void setIdAuteur(int idAuteur) {
		this.idAuteur = idAuteur;
	}
	@SuppressWarnings("unused")
	private int idAuteur;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateDeParition() {
		return dateDeParition;
	}
	public void setDateDeParition(Date dateDeParition) {
		this.dateDeParition = dateDeParition;
	}
	public int getNombreDePage() {
		return nombreDePage;
	}
	public void setNombreDePage(int nombreDePage) {
		this.nombreDePage = nombreDePage;
	}
	public byte[] getpDF() {
		return pDF;
	}
	public void setpDF(byte[] pDF) {
		this.pDF = pDF;
	}
	public byte[] getAudio() {
		return audio;
	}
	public void setAudio(byte[] audio) {
		this.audio = audio;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	private LivreItem item;
	public LivreItem getItem() {
		return item;
	}
	public void setItem() {
		item.setTitre(titre);
        item.setCouverture(couverture);
	}
	public Livre() {
		 item=new LivreItem();
	}
	
	
	
	private int nombreEtoiles;
	public int getNombreEtoiles() {
		return nombreEtoiles;
	}
	public void setNombreEtoiles(int nombreEtoiles) {
		this.nombreEtoiles = nombreEtoiles;
	}
	
	private String extrait;
	public String getExtrait() {
		return extrait;
	}
	public void setExtrait(String extrait) {
		this.extrait = extrait;
	}
	
	
	
	
}
