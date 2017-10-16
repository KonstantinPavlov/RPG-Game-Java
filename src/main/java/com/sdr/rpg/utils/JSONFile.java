package com.sdr.rpg.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Konstantin on 16.10.2017.
 */
public class JSONFile {

    public static final JSONObject parseFromResource(String resource) {
        return parseFromString(TextFile.readFromResource(resource));
    }

    public static final JSONObject parseFromString(String json) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(TextFile.readFromResource("/js/jsonparser.js"));
            return new JSONObject(((Invocable) engine).invokeFunction("parseJSON", json));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

}
