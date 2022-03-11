package model;

public class LivreItem {

        private String titre;
        private byte[] couverture;
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
		public byte[] getCouverture() {
			return couverture;
		}
		public void setCouverture(byte[] couverture) {
			this.couverture = couverture;
		}
}
