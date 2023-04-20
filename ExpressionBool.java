/**
 * @author Lazaar Mehdi
 * @version Avril 2023
 */
public class ExpressionBool{
	protected Noeud racine;
    //Les constructeurs

	/**
	 * Constructeur par défaut qui met la racine à null
	 */
	public ExpressionBool(){
		racine = null;
	}
	/**
     * Constructeur avec un noeud en parametre
	 * @param r un noeud
	 */
	public ExpressionBool(Noeud r){
		racine = r;
	}
    /**
     * @return la racine d'un noeud
     */
    public Noeud getRacine(){
        return racine;
    }
    /**
     * @param b represente une expression booleenne qui sera utilisé comme fils droit
     * @return une expression booleenne avec comme racine (l'opérateur de la disjonction (OU qui sera noté +))
     * et deux fils gauche et droit
     */
    public ExpressionBool disjonction(ExpressionBool b) {
        ExpressionBool expression = new ExpressionBool(new Noeud('+'));
        expression.getRacine().setFilsGauche(this.getRacine());
        expression.getRacine().setFilsDroit(b.getRacine());
        return expression;
    }
    /**
     * @param b represente une expression booleenne qui sera utilisé comme fils droit
     * @return une expression booleenne avec comme racine (l'opérateur de la conjonction (ET qui sera noté avec un .))
     * et deux fils gauche et droit
     */
    public ExpressionBool conjonction(ExpressionBool b) {
        ExpressionBool expression = new ExpressionBool(new Noeud('.'));
        expression.getRacine().setFilsGauche(this.getRacine());
        expression.getRacine().setFilsDroit(b.getRacine());
        return expression;
    }
    /**
     * @return une expression booleenne avec comme racine (l'opérateur de la négation (NON qui sera noté !)) avec un fils droit
     */
    public ExpressionBool negation() {
        ExpressionBool expression = new ExpressionBool(new Noeud('!'));
        expression.getRacine().setFilsDroit(this.getRacine());
        return expression;
    }
    /**
     * Méthode d'affichage de l'expression booleenne avec un parcours infixe (c-a-d fils gauche, racine, fils droit)
     */
    public String afficher() {
        return afficher(racine); 
    }
    private String afficher(Noeud a){
        String expB = "";
        if(a != null){
            if(a.getFilsGauche() != null){
                expB += "(" + afficher(a.getFilsGauche()) + ")";
            }
            expB += " " + a.getElement() + " ";
            if(a.getFilsDroit() != null){
                expB += "(" + afficher(a.getFilsDroit()) + ")";
            }
        }
        return expB;
    }
    public boolean evaluation(boolean [] vecteur){
        return evaluationRec(racine, vecteur);
    }
    private boolean evaluationRec(Noeud a, boolean[] vecteur){
        if(a.getElement() == '+'){
            return evaluationRec(a.getFilsGauche(), vecteur) || evaluationRec(a.getFilsDroit(), vecteur);
        } else if(a.getElement() == '.'){
            return evaluationRec(a.getFilsGauche(), vecteur) && evaluationRec(a.getFilsDroit(), vecteur);
        } else if(a.getElement() == '!'){
            return !(evaluationRec(a.getFilsDroit(),vecteur));
        }
        //Code ASCII - a
        for(int i = 0; i < 26; i++){
            if(a.getElement() == (char)('a' + i)){
                return vecteur[i];
            }
        }
        return false;
    }
    public String toString(){
        return " ";
    }
    public static void main(String[] args) {
        boolean vect1[] = {true,true,false,true,true};
        boolean vect2[] = {false,true,false,true,false};
        ExpressionBool a = new ExpressionBool(new Noeud('a'));
        ExpressionBool b = new ExpressionBool(new Noeud('b'));
        ExpressionBool c = new ExpressionBool(new Noeud('c'));
        ExpressionBool d = new ExpressionBool(new Noeud('d'));
        System.out.println("Les valeurs booleennes selon le 1er vecteur");
        int j = 0;
        for(char i = 'a'; i <= 'e' && j < 5; i++, j++){
            System.out.println("La valeur de " + i + " est : " + vect1[j]);
        }
        //OU = disjonction  (+)(v)
        //ET = conjonction  (.)(^)
        //NON = négation    (!)(¬)
        //On étudie l'expression suivante : ¬(( ¬a ∧ (c ∨ a))
        ExpressionBool expression1 = a.negation();
        ExpressionBool expression2 = c.disjonction(a);
        ExpressionBool expression3 = expression1.conjonction(expression2);
        ExpressionBool expression4f = expression3.negation();
        //On étudie l'expression suivante : ¬(b ∧  d))
        ExpressionBool expression5 = b.conjonction(d);
        ExpressionBool expression6f = expression5.negation();
        //On étudie l'expression suivante : ¬(( ¬a ∧ (c ∨ a)) ∨¬(b ∧  d))
        ExpressionBool expressionFinal = expression4f.disjonction(expression6f);
        System.out.println(expressionFinal.afficher());
        System.out.println("\n");
        System.out.println("Evaluation avec le vecteur n°1 : ");
        System.out.println("f = " + expressionFinal.evaluation(vect1));
        System.out.println("\n");
        System.out.println("Les valeurs booleennes selon le 2eme vecteur");
        int k = 0;
        for(char i = 'a'; i <= 'e' && k < 5; i++, k++){
            System.out.println("La valeur de " + i + " est : " + vect2[k]);
        }
        System.out.println("Evaluation avec le vecteur n°2 : ");
        System.out.println("f = " + expressionFinal.evaluation(vect2));
        System.out.println("--------------------------------------------------------------------------------------------------------");
        ExpressionBool exp1 = a.negation(); //!a
        ExpressionBool exp2 = b.conjonction(c); // b^c
        ExpressionBool exp3f = exp1.disjonction(exp2); // !a v (b^c)
        ExpressionBool exp4f = c.negation(); // !c
        ExpressionBool expFinal = exp3f.conjonction(exp4f);
        System.out.println(expFinal.afficher());
        System.out.println("\n");
        System.out.println("Evaluation avec le vecteur n°1 : ");
        System.out.println("e = " + expFinal.evaluation(vect1));
        System.out.println("\n");
        System.out.println("Evaluation avec le vecteur n°2 : ");
        System.out.println("e = " + expFinal.evaluation(vect2));
    }
}