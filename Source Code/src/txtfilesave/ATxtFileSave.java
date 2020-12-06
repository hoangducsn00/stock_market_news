package txtfilesave;

public abstract class ATxtFileSave {
	protected final static String FOLDER_URL = "D:\\OOP\\";

	public ATxtFileSave() {

	}

	public static String getFolderUrl() {
		return FOLDER_URL;
	}

	public abstract void storage(String dataStorage) ;
}
