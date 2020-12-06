package txtfilesave;

public abstract class ATxtFileSave {
	protected final static String FOLDER_URL = "D:\\OneDrive - Hanoi University of Science and Technology\\Object-Oriented Programming\\OOP Coursework\\Workspace\\";

	public ATxtFileSave() {

	}

	public static String getFolderUrl() {
		return FOLDER_URL;
	}

	public abstract void storage(String dataStorage) ;
}
