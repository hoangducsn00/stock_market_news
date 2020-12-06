package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class RedirectOutput {

	private JTextComponent textComponent;
	
	public static void sendTo(JTextComponent textComponent) {
		new RedirectOutput(textComponent).redirectSystemStreams();
	}
	
	private RedirectOutput(JTextComponent textComponent) {
		this.textComponent = textComponent;
		textComponent.setEditable( false );
		textComponent.setBackground(new Color(255, 255, 204)); //cài đặt màu background cho output
		textComponent.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	private void updateTextComponent(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Document doc = textComponent.getDocument();
				try {
					doc.insertString(doc.getLength(), text, null);
				} catch (BadLocationException e) {
					throw new RuntimeException(e);
				}
				textComponent.setCaretPosition(doc.getLength() - 1);
			}
		});
	}

	private void redirectSystemStreams() {
		OutputStream out = new OutputStream() {
			@Override
			public void write(final int b) throws IOException {
				updateTextComponent(String.valueOf((char) b));
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				updateTextComponent(new String(b, off, len));
			}

			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
		};
		try {
			System.setOut(new PrintStream(out, true, "UTF-8"));
			System.setErr(new PrintStream(out, true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Không hỗ trợ encoding UTF-8");
		}

	}

}

