package me.thatonerr.slayone.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.awt.event.MouseEvent.*;

public class Utils {

    public static BufferedImage getImageResource(String filePath) {
        try {
            return ImageIO.read(Utils.class.getResource(filePath));
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(2);
        }
        return null;
    }

    public static String getTextFileResource(String filePath) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources" + filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(2);
        }
        return builder.toString();
    }

    public static Font getFontResource(String filePath, int fontSize) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Utils.class.getResourceAsStream(filePath)).deriveFont((float) fontSize);
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
            System.exit(2);
        }
        return null;
    }

    public static String getClickTextSF(int keycode) {
        switch (keycode) {
            case BUTTON1:
                return "B1";
            case BUTTON2:
                return "B2";
            case BUTTON3:
                return "B3";
            default:
                return "None";
        }
    }

    public static String getKeyTextSF(int keyCode) {
        if (keyCode == -1) {
            return "None";
        }
        String text = KeyEvent.getKeyText(keyCode).toLowerCase(), newText;
        if (text.startsWith("unknown")) {
            return "None";
        }
        if (text.startsWith("numpad")) {
            newText = "Num" + text.replace("numpad", "").replace("-", "");
        } else if (text.equalsIgnoreCase("backspace")) {
            newText = "Back";
        } else if (text.equalsIgnoreCase("context menu")) {
            newText = "CMenu";
        } else if (text.equalsIgnoreCase("open bracket")) {
            newText = "[";
        } else if (text.equalsIgnoreCase("close bracket")) {
            newText = "]";
        } else if (text.equalsIgnoreCase("back quote")) {
            newText = "`";
        } else if (text.equalsIgnoreCase("back slash")) {
            newText = "\\";
        } else if (text.equalsIgnoreCase("quote")) {
            newText = "'";
        } else if (text.equalsIgnoreCase("semicolon")) {
            newText = ";";
        } else if (text.equalsIgnoreCase("comma")) {
            newText = ",";
        } else if (text.equalsIgnoreCase("period")) {
            newText = ".";
        } else if (text.equalsIgnoreCase("caps lock")) {
            newText = "Caps";
        } else if (text.equalsIgnoreCase("minus")) {
            newText = "-";
        } else if (text.equalsIgnoreCase("equal")) {
            newText = "=";
        } else if (text.equalsIgnoreCase("escape")) {
            return "None";
        } else if (text.equalsIgnoreCase("slash")) {
            newText = "/";
        } else {
            newText = text.substring(0, 1).toUpperCase() + text.substring(1);
        }
        return newText;
    }

}
