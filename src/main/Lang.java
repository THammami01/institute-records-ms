package main;

public class Lang {
	public static String getEquiv(String s) {
		String lang = Controller.lang;

		if (lang.equals("french")) return s;

		switch (s) {
			case "Modifier":
				switch (lang) {
					case "arabic":
						return "تعديل";
					case "english":
						return "Modify";
				}
				break;

			case "Retourner":
				switch (lang) {
					case "arabic":
						return "رجوع";
					case "english":
						return "Return";
				}
				break;

			case "Tous les champs doivent être remplis.":
				switch (lang) {
					case "arabic":
						return "يجب ملأ كل المجالات.";
					case "english":
						return "All Fields Must be Filled.";
				}
				break;

			case "Numéro de CIN invalide.":
				switch (lang) {
					case "arabic":
						return "رقم بطاقة التعريف غير صحيح.";
					case "english":
						return "Invalid ID Card Number.";
				}
				break;

			case "La classe saisie n'existe pas.":
				switch (lang) {
					case "arabic":
						return "القسم غيم موجود.";
					case "english":
						return "Entered Class Does Not Exist.";
				}
				break;

			case "Ajouté avec succès.":
				switch (lang) {
					case "arabic":
						return "تمّت الإضافة بنجاح.";
					case "english":
						return "Added Successfully.";
				}
				break;

			case "Erreur lors de l'ajout.":
				switch (lang) {
					case "arabic":
						return "لم تتم الإضافة.";
					case "english":
						return "Error While Attempting to Add.";
				}
				break;

			case "Enregistrer":
				switch (lang) {
					case "arabic":
						return "تسجيل";
					case "english":
						return "Save";
				}
				break;

			case "Annuler":
				switch (lang) {
					case "arabic":
						return "إلغاء";
					case "english":
						return "Undo";
				}
				break;

			case "Modifié avec succès.":
				switch (lang) {
					case "arabic":
						return "تمّ التعديل بنجاح.";
					case "english":
						return "Modified Successfully.";
				}
				break;

			case "Erreur lors de la modification.":
				switch (lang) {
					case "arabic":
						return "لم تتم عمليّة التعديل.";
					case "english":
						return "Error While Attempting to Modify.";
				}
				break;

			case "Supprimer Étudiant":
				switch (lang) {
					case "arabic":
						return "حذف طالب";
					case "english":
						return "Delete Student";
				}
				break;

			case "Voulez-vous vraiment supprimer cet étudiant ?":
				switch (lang) {
					case "arabic":
						return "هل أنت متأكد أنك تريد حذف هذا الطالب ؟";
					case "english":
						return "Are you sure you want to delete this student ?";
				}
				break;

			case "Supprimé avec succès.":
				switch (lang) {
					case "arabic":
						return "تمّ الحذف بنجاح.";
					case "english":
						return "Deleted Successfully.";
				}
				break;

			case "Erreur lors de la suppression.":
				switch (lang) {
					case "arabic":
						return "لم تتم عمليّة الحذف.";
					case "english":
						return "Error While Attempting to Delete.";
				}
				break;

			case "Aucune langue sélectionnée.":
				switch (lang) {
					case "arabic":
						return "لا يتم إختيار أي لغة.";
					case "english":
						return "No Selected Language.";
				}
				break;

			case "Langue enregistrée.":
				switch (lang) {
					case "arabic":
						return "تم تسجيل اللغة.";
					case "english":
						return "Language Saved.";
				}
				break;

			case "Erreur lors de l'enregistrement.":
				switch (lang) {
					case "arabic":
						return "لم يتم تسجيل اللغة.";
					case "english":
						return "Error While Attempting to Save.";
				}
				break;

			case "Bonjour !":
				switch (lang) {
					case "arabic":
						return "صباح الخير !";
					case "english":
						return "Good Morning !";
				}
				break;

			case "Bon après-midi !":
				switch (lang) {
					case "arabic":
						return "مساء الخير !";
					case "english":
						return "Good Afternoon !";
				}
				break;

			case "Bonsoir !":
				switch (lang) {
					case "arabic":
						return "مساء الخير !";
					case "english":
						return "Good Evening !";
				}
				break;

			case "Choisir Document":
				switch (lang) {
					case "arabic":
						return "إخيار وثيقة";
					case "english":
						return "Select Document";
				}
				break;

			case "Erreur lors de l'importation des documents.":
				switch (lang) {
					case "arabic":
						return "لم تتم عملية تحميل الوثيقة.";
					case "english":
						return "Error While Attempting to Import Documents.";
				}
				break;

			case "Erreur lors de l'ajout des documents.":
				switch (lang) {
					case "arabic":
						return "لم تتم إضافة الوثائق.";
					case "english":
						return "Error While Attempting to Add Docuements.";
				}
				break;

			case " document ajouté avec succès.":
				switch (lang) {
					case "arabic":
						return " وثيقة أضيفة.";
					case "english":
						return " Document Added Successfully.";
				}
				break;

			case " documents ajoutés avec succès.":
				switch (lang) {
					case "arabic":
						return " وثائق أضيفة.";
					case "english":
						return " Documents Added Successfully.";
				}
				break;

			case "Aucun document.":
				switch (lang) {
					case "arabic":
						return "لا يوجد أي وثيقة.";
					case "english":
						return "No Document.";
				}
				break;

			case "Supprimer Tous Documents":
				switch (lang) {
					case "arabic":
						return "مسح جميع الوثائق.";
					case "english":
						return "Delete All Documents.";
				}
				break;

			case "Voulez-vous vraiment supprimer tous les documents de cet étudiant ?":
				switch (lang) {
					case "arabic":
						return "هل أنت متأكد أنّك تريد حذف كل وثائق هذا الطالب ؟";
					case "english":
						return "Are You Sure You Want to Delete All Documents of This Students ?";
				}
				break;

			case "Tous les documents sont supprimés avec succès.":
				switch (lang) {
					case "arabic":
						return "تم حذف كلّ الوثائق.";
					case "english":
						return "All Documents Deleted Successfully.";
				}
				break;

			case "Erreur lors de la suppression de tous les documents.":
				switch (lang) {
					case "arabic":
						return "لم يتم حذف كل الوثائق.";
					case "english":
						return "Error While Attempting to Delete All Documents.";
				}
				break;

			case "Erreur lors de l'ouverture du document.":
				switch (lang) {
					case "arabic":
						return "لم تتم عمليّة فتح الوثيقة.";
					case "english":
						return "Error While Attempting to Open Document.";
				}
				break;

			case "Supprimer Document":
				switch (lang) {
					case "arabic":
						return "حذف وثيقة";
					case "english":
						return "Delete Document";
				}
				break;

			case "Voulez-vous vraiment supprimer cet document ?":
				switch (lang) {
					case "arabic":
						return "هل أنت متأكد أنّك تريد حذف هذه الوثيقة ؟";
					case "english":
						return "Are You Sure You Want to Delete This Document ?";
				}
				break;

			case "1 document supprimé avec succès.":
				switch (lang) {
					case "arabic":
						return "1 وثيقة حذفت بنجاح.";
					case "english":
						return "1 Document Deleted Succssfully.";
				}
				break;

			case "Erreur lors de la suppression du document.":
				switch (lang) {
					case "arabic":
						return "لم تتم عمليّة حذف الوثيقة.";
					case "english":
						return "Error While Attempting to Delete Document.";
				}
				break;

			case "Entrer un numéro de CIN d'abord.":
				switch (lang) {
					case "arabic":
						return "أدخل رقم بطاقة التعريف أوّلا.";
					case "english":
						return "Enter ID Card Number First.";
				}
				break;

			case "Aucun étudiant enregistré avec ce numéro de CIN.":
				switch (lang) {
					case "arabic":
						return "لا يوجد أي طالب مسجّل برقم بطاقة التعريف هذه.";
					case "english":
						return "No Student Found under this ID Card Number.";
				}
				break;

			case "Oui":
				switch (lang) {
					case "arabic":
						return "نعم";
					case "english":
						return "Yes";
				}
				break;

			case "Non":
				switch (lang) {
					case "arabic":
						return "لا";
					case "english":
						return "No";
				}
				break;

			case "Aucune classe selectionnée.":
				switch (lang) {
					case "arabic":
						return "لم يتم إختيار أي قسم.";
					case "english":
						return "No Selected Class.";
				}
				break;
		}

		return s;
	}
}