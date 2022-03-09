package interacoes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InteracaoDeTecladoEcliqueDoMouse {
	
	default Robot intanciarRobot() {
		Robot r = null;
			try {
				r = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return r;
	}

	default void moverParaCima() { intanciarRobot().keyPress(KeyEvent.VK_UP); }

	default void moverParaBaixo() { intanciarRobot().keyPress(KeyEvent.VK_DOWN); }

	default void moverParaEsquerda() { intanciarRobot().keyPress(KeyEvent.VK_LEFT); }

	default void moverParaDireita() { intanciarRobot().keyPress(KeyEvent.VK_RIGHT); }

	default void pressionarEnter() { intanciarRobot().keyPress(KeyEvent.VK_ENTER); }

	default void backspace(int quantidade) {
		for (int i = 1; i <= quantidade; i++) { intanciarRobot().keyPress(KeyEvent.VK_BACK_SPACE); }
	}

	default void delete(int quantidade) {
		for (int i = 1; i <= quantidade; i++) { intanciarRobot().keyPress(KeyEvent.VK_DELETE); }
	}

	default void espaco(int quantidade) {
		for (int i = 1; i <= quantidade; i++) { intanciarRobot().keyPress(KeyEvent.VK_SPACE); }
	}

	default void esc() { intanciarRobot().keyPress(KeyEvent.VK_ESCAPE); }

	default void control() { intanciarRobot().keyPress(KeyEvent.VK_CONTROL); }

	default void shift() { intanciarRobot().keyPress(KeyEvent.VK_SHIFT); }

	default void alt() { intanciarRobot().keyPress(KeyEvent.VK_ALT); }

	default void pageUp() { intanciarRobot().keyPress(KeyEvent.VK_PAGE_UP); }
	 
	default void pageDown() { intanciarRobot().keyPress(KeyEvent.VK_PAGE_DOWN); }

	default void end() { intanciarRobot().keyPress(KeyEvent.VK_END); }

	default void home() { intanciarRobot().keyPress(KeyEvent.VK_HOME); }

	default void windows() { intanciarRobot().keyPress(KeyEvent.VK_WINDOWS); }
	 
	default void tab() { intanciarRobot().keyPress(KeyEvent.VK_TAB); }

	default void capsLock() { intanciarRobot().keyPress(KeyEvent.VK_CAPS_LOCK); }

	default void copiar() { intanciarRobot().keyPress(KeyEvent.VK_COPY); }
	
	default void cortar() { intanciarRobot().keyPress(KeyEvent.VK_CUT); }
	 
	default void colar() { intanciarRobot().keyPress(KeyEvent.VK_PASTE); }

	default void clicarBotaoEsquerdoMouse(int quantidade) {
		for (int i = 0; i <= quantidade; i++) { intanciarRobot().mousePress(MouseEvent.BUTTON1); }
	}
	
	default void clicarBotaoDireitoMouse(int quantidade) {
		for (int i = 0; i <= quantidade; i++) { intanciarRobot().mousePress(MouseEvent.BUTTON2); }
	}

	default void rolar(int quantidadeRolagem) { intanciarRobot().mouseWheel(quantidadeRolagem); }

	
	default void moverParaCoordenada(int coordenadaX, int coordenadaY) { intanciarRobot().mouseMove(coordenadaX, coordenadaY); }

	default void digitar(CharSequence characters) {
		int length = characters.length();
		for (int i = 0; i < length; i++) {
			char character = characters.charAt(i);
			tipoCaracteres(character);
		}
	}

	default void tipoCaracteres(char caracteres) {

		switch (caracteres) {
		case 'a':
			tipoCaracteres(KeyEvent.VK_A);
			break;
		case 'b':
			tipoCaracteres(KeyEvent.VK_B);
			break;
		case 'c':
			tipoCaracteres(KeyEvent.VK_C);
			break;
		case 'd':
			tipoCaracteres(KeyEvent.VK_D);
			break;
		case 'e':
			tipoCaracteres(KeyEvent.VK_E);
			break;
		case 'f':
			tipoCaracteres(KeyEvent.VK_F);
			break;
		case 'g':
			tipoCaracteres(KeyEvent.VK_G);
			break;
		case 'h':
			tipoCaracteres(KeyEvent.VK_H);
			break;
		case 'i':
			tipoCaracteres(KeyEvent.VK_I);
			break;
		case 'j':
			tipoCaracteres(KeyEvent.VK_J);
			break;
		case 'k':
			tipoCaracteres(KeyEvent.VK_K);
			break;
		case 'l':
			tipoCaracteres(KeyEvent.VK_L);
			break;
		case 'm':
			tipoCaracteres(KeyEvent.VK_M);
			break;
		case 'n':
			tipoCaracteres(KeyEvent.VK_N);
			break;
		case 'o':
			tipoCaracteres(KeyEvent.VK_O);
			break;
		case 'p':
			tipoCaracteres(KeyEvent.VK_P);
			break;
		case 'q':
			tipoCaracteres(KeyEvent.VK_Q);
			break;
		case 'r':
			tipoCaracteres(KeyEvent.VK_R);
			break;
		case 's':
			tipoCaracteres(KeyEvent.VK_S);
			break;
		case 't':
			tipoCaracteres(KeyEvent.VK_T);
			break;
		case 'u':
			tipoCaracteres(KeyEvent.VK_U);
			break;
		case 'v':
			tipoCaracteres(KeyEvent.VK_V);
			break;
		case 'w':
			tipoCaracteres(KeyEvent.VK_W);
			break;
		case 'x':
			tipoCaracteres(KeyEvent.VK_X);
			break;
		case 'y':
			tipoCaracteres(KeyEvent.VK_Y);
			break;
		case 'z':
			tipoCaracteres(KeyEvent.VK_Z);
			break;
		case 'A':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
			break;
		case 'B':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'C':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
			break;
		case 'D':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
			break;
		case 'E':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
			break;
		case 'F':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
			break;
		case 'G':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
			break;
		case 'H':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
			break;
		case 'I':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
			break;
		case 'J':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
			break;
		case 'K':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'L':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'M':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'N':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'O':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'P':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'Q':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'R':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'S':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'T':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'U':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'V':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'W':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'X':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'Y':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'Z':
			tipoCaracteres(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case '0':
			tipoCaracteres(KeyEvent.VK_0);
			break;
		case '1':
			tipoCaracteres(KeyEvent.VK_1);
			break;
		case '2':
			tipoCaracteres(KeyEvent.VK_2);
			break;
		case '3':
			tipoCaracteres(KeyEvent.VK_3);
			break;
		case '4':
			tipoCaracteres(KeyEvent.VK_4);
			break;
		case '5':
			tipoCaracteres(KeyEvent.VK_5);
			break;
		case '6':
			tipoCaracteres(KeyEvent.VK_6);
			break;
		case '7':
			tipoCaracteres(KeyEvent.VK_7);
			break;
		case '8':
			tipoCaracteres(KeyEvent.VK_8);
			break;
		case '9':
			tipoCaracteres(KeyEvent.VK_9);
			break;
		default:
			throw new IllegalArgumentException("Não foi localizado a letra digitada ");
		}
	}

	default void tipoCaracteres(int... keyCodes) { tipoCaracteres(keyCodes, 0, keyCodes.length); }

	default void tipoCaracteres(int[] keyCodes, int offset, int length) {
		if (length == 0) {
			return;
		}
		intanciarRobot().keyPress(keyCodes[offset]);
		tipoCaracteres(keyCodes, offset + 1, length - 1);
		intanciarRobot().keyRelease(keyCodes[offset]);
	}
}