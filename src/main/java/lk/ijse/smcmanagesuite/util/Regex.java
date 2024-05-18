package lk.ijse.smcmanagesuite.util;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFiledsValid(TextFields textFields, String text){
        String field = "";

        switch (textFields){
            case ID :
                field = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                field = "^(([A-z|\\\\\\s]{3,})| ([\\\\\\s][A-z|\\\\\\s]{3,}))$";
                break;
            case PHONE:
                field = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case ADDRESS:
                field = "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$";
                break;
            case UID:
                field = "^(([U])([0-9]{3}))$";
                break;
            case DESC:
                field = "^([A-z|\\s]{4,})$";
                break;
            case CID:
                field = "^(([C])([0-9]{3}))$";
                break;
            case SID:
                field = "^(([S])([0-9]{3}))$";
                break;
            case EID:
                field = "^(([E])([0-9]{3}))$";
                break;
            case IID:
                field = "^(([I])([0-9]{3}))$";
                break;
            case QTY:
                field = "^([\\d]{3}|[\\d]{2}|[\\d]{1})$";
                break;
            case PRICE:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case PW:
                field = "^(([A-z]{5})([\\W])([0-9]{4}))|(([A-z]{4})([\\W])([0-9]{4}))|(([A-z]{6})([\\W])([0-9]{4}))|(([A-z]{7})([\\W])([0-9]{4}))|(([A-z]{8})([\\W])([0-9]{4}))$";
                break;
            case UNAME:
                field = "^([A-z|\\\\s]{3,})$";
                break;
        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextFields location, JFXTextField field){
        if (Regex.isTextFiledsValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("Green"));
            field.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("Red"));
            field.setUnFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }
    public static boolean setTextColor(TextFields location, JFXPasswordField field){
        if (Regex.isTextFiledsValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("Green"));
            field.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("Red"));
            field.setUnFocusColor(Paint.valueOf("Red"));
            return false;
        }
    }
}
