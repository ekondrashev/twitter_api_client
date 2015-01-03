import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class copyFile {

	public static void main(String[] args)throws IOException {
		File source = new File("C:\\text.txt");
		// проверяем существование файла text.xtx (созданного)
		if (source.isFile()) {
			System.out.println("путь файла: " + source.getPath() + " имя файла: "
					+ source.getName());
		}
		// создаем обьект принимаемого файла
		File dest = new File("D:\\dest.txt");

		// созадем входной поток
		FileInputStream is = new FileInputStream(source);
		try {
			// создаем выходной поток
			FileOutputStream os = new FileOutputStream(dest);
			// копируем побайтово файла
			try {
				byte[] buffer = new byte[4096];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			} finally {
				os.close();
			}
		} finally {
			is.close();
		}
		if (dest.isFile()) {
			System.out.print("путь файла: " + dest.getPath() + " имя файла: "
					+ dest.getName());
		}
	}
}
