public static void ecrireFichier(ArbreAbstrait arbre, String nomdefichier)
			throws IOException {
		
                String code = arbre.getCodeFinal();
                File fichier = new File(nomdefichier);

		try {
			FileOutputStream fos = new FileOutputStream(fichier);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Writer ecriture = null;
		try {
			ecriture = new OutputStreamWriter(new FileOutputStream(fichier));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter ficTexte = null;

		try {

			ficTexte = new BufferedWriter(new FileWriter(nomdefichier));
			ficTexte.write(code);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
