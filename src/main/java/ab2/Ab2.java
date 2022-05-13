package ab2;

public interface Ab2
{
    /**
     * Lierfert eine HashMap-Implementierung mit linearer Sondierungsstrategie.
     * Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public AbstractHashMap newHashMapLinear(int minSize);

    /**
     * Liefert eine HashMap-Implementierung mit qudratischer
     * Sondierungsstrategie. Die Hashtabelle soll mindestens minSize Elemente
     * aufnehmen können.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public AbstractHashMap newHashMapQuadratic(int minSize);

    /**
     * Liefert eine HashMap-Implementierung mit doppeltem Hashen. Die
     * Hashtabelle soll mindestens minSize Elemente aufnehmen können.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public AbstractHashMap newHashMapDouble(int minSize);

    /**
     * Quickselect-Implementierung zur Bestimmung des i-kleinsten Elements in
     * einem unsortierten Array. Achtung: Das i-kleinste Element steht im
     * sortierten Array an Stelle i-1!
     *
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param i Der Index i für das i-kleinste Element (beginnend bei "1" für
     * das kleinste Element).
     *
     * @return das i-kleinste Element im Array
     */
    public int quickselect(int[] data, int i);
}
