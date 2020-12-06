package txtfilesave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class UPCOMNewsTxtSave extends ATxtFileSave implements ITxtFileSave{
	private final static String FILE_URL_UPCOM = (getFolderUrl() + "UPCOM.txt");
	public UPCOMNewsTxtSave() {
		
	}
	
	@Override
	public void storage(String dataStorage)  {
		try {
	        File file = new File(FILE_URL_UPCOM);
	        OutputStream outputStream = new FileOutputStream(file, true);
	        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8")) {
				outputStreamWriter.write(dataStorage);
				// Dùng để xuống hàng
				outputStreamWriter.write("\n");
				// Đây là phương thức quan trọng!
				// Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
				outputStreamWriter.flush();
        }
			
		} catch (IOException e) {
			System.out.println("Không thể ghi dữ liệu ra file txt");
		}
	}
}