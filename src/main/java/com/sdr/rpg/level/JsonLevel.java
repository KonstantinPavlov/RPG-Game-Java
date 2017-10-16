package com.sdr.rpg.level;

import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.*;

import javax.xml.soap.Node;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.net.URL;

import static java.awt.SystemColor.text;

/**
 * Created by Konstantin on 16.10.2017.
 */
public class JsonLevel extends Level {


    public JsonLevel(String path) {
        super(path);
    }

    @Override
    protected void loadLevel(String path) {



    }

    @Override
    protected void generateLevel() {

    }


    private void parseJson() {


    }




    public static Object parse(final Object text, final Object reviver) {
        final String str = JSType.toString(text);
        final Global global = Context.getGlobal();
//        final boolean dualFields = ((ScriptObject) global).useDualFields();
        final JSONParser parser = new JSONParser(str, global, true);
        final Object value;
        try {
            value = parser.parse();
        } catch (final ParserException e) {
            throw ECMAErrors.syntaxError(e, "invalid.json", e.getMessage());
        }
        return  value;
//        return applyReviver(global, value, reviver);
    }

}
