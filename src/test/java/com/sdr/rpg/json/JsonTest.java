package com.sdr.rpg.json;

import com.sdr.rpg.utils.JSONArray;
import com.sdr.rpg.utils.JSONFile;
import com.sdr.rpg.utils.JSONObject;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 16.10.2017.
 */
public class JsonTest {

    public static void main(String[] args) {
        parse();
    }

    public static void parse() {
        JSONArray layers = JSONFile.parseFromResource("/maps/level_alpha.json").getArray("layers");
        for (int i = 0; i < layers.length(); i++) {
            JSONObject layer = layers.getObject(i);
            System.out.println( layer);
            JSONArray data = layer.getArray("data");
            for (int j = 0; j <data.length() ; j++) {
                System.out.println("Object in Layer with j = "+ j + " is =" +data.getInteger(j));
            }
        }
    }

}
