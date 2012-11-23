package CaesarViginere;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaesarModel ca = new CaesarModel();
		ca.setPlain("ichliebemeinbaerchen");
		ca.setKey('x');
		ca.chiffrierenCaesar();

	}

}
