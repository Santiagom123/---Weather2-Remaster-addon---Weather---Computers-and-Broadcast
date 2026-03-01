package com.santiagom123.weather_broadcasting;

public class Utils {



    /**
     * Maps a value from one range to another (Linear Interpolation).
     * Standard utility for converting user input (e.g., 20-200 Hz)
     * to Minecraft's pitch system (0.5 - 2.0).
     *
     * @param val The input value from Lua.
     * @param inMin Input range lower bound.
     * @param inMax Input range upper bound.
     * @param outMin Output range lower bound (MC Pitch min 0.5).
     * @param outMax Output range upper bound (MC Pitch max 2.0).
     * @return The mapped float value for playSound().
     */
    public static float map(float val, float inMin, float inMax, float outMin, float outMax) {
        return (val - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }


}
