/**
 * @author Lazaar El Mahdi
 * @version Avril 2023
 */
public class Noeud{
	//Les attributs
	protected char element;
	protected Noeud filsGauche;
	protected Noeud filsDroit;

	//Constructeurs
	/**
	 * Constructeur par défaut qui initialise les deux fils gauche et droit en null 
	 * et affecte un objet de type caractere (char) à l attribut elemnt
	 * @param _element
	 */
	public Noeud(char _element){
		filsGauche = null;
		filsDroit = null;
		element = _element;
	}
	/**
	 * Constructeur par défaut
	 * @param g represente le fils gauche
	 * @param d represente le fils droit
	 * @param c un objet de type caractere (char)
	 */
	public Noeud(Noeud g, Noeud d, char c){
		filsGauche = g;
		filsDroit = d;
		element = c;
	}
	// Accesseurs
	/** 
	 * Méthode qui permet de retourner un fils gauche
	 * @return le fils gauche
	 */
	public Noeud getFilsGauche(){
		return filsGauche;
	}
	/**
	 * Méthode qui permet de retourner un fils droit
	 * @return le fils droit
	 */
	public Noeud getFilsDroit(){
		return filsDroit;
	}
	/**
	 * Méthode qui permet d'aaccéder à l'attribut (element) d'un noeud et le retourner
	 * @return un caractere (element)
	 */
	public char getElement(){
		return element;
	}
	//Modificateurs
	/**
	 * Méthode qui prend comme argument _fils_Gauche de type Noeud
	 * et qui permet de définir le fils gauche du noeud courant comme étant _fils_Gauche
	 * @param _fils_Gauche
	 */
	public void setFilsGauche(Noeud _fils_Gauche){
		filsGauche = _fils_Gauche;
	}
	/**
	 * Méthode qui prend comme argument _fils_Droit de type Noeud
	 * et qui permet de définir le fils droit du noeud courant comme étant _fils_Droit
	 * @param _fils_Droit
	 */
	public void setFilsDroit(Noeud _fils_Droit){
		filsDroit = _fils_Droit;
	}
	/**
	 * Méthode qui comme en paramtre un objet a de type caractere et qui l affecte à l'élément du noeud courant
	 * @param a
	 */
	public void setElement(char a){
		element = a;
	}
	@Override
	public String toString(){
		if(element == '\0'){
			return "nil";
		}
		String res = new String();
		res = "Element : " + element + " FilsG : " + getFilsGauche()+ "," + " filsD : " + getFilsDroit();
		return res;
	}
	public static void main(String[] args) {
		Noeud a = new Noeud(new Noeud('b'),new Noeud('c'),'a');
		System.out.println(a);
		a.setElement('v');
		System.out.println(a);
		a.setFilsGauche(new Noeud('l'));
		a.setFilsDroit(new Noeud('v'));
		System.out.println(a);
		System.out.println(a.getElement());
		System.out.println(a.getFilsGauche().getElement());
		System.out.println(a.getFilsDroit().getElement());
	}
}