/**
 * User: ko
 * Date: 25/08/13
 * Time: 20:41
 */
class T9SpellingApplication {

    public static void main(String[] args) {
        new T9SpellingApplication().parseFile(new File("C-small-practice.in"), new FileOutputStream("C-small-practice.out"))
        new T9SpellingApplication().parseFile(new File("C-large-practice.in"), new FileOutputStream("C-large-practice.out"))
    }

    private static Map<Character, Key> createT9Mapping(){
        Map<Character, Key> retValue = [:]
        int key = 1
        ["", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"].each {
            it.chars.eachWithIndex { c, timesPressed ->
                retValue.put(c, new Key(c, key, timesPressed + 1))
            }
            key++
        }

        retValue.put(' ' as char, new Key(' ' as char, 0, 1))
        return retValue
    }

    private static final Map<Character, Key> mapping = createT9Mapping()

    void parseFile(File file, OutputStream outputStream) {
        List<String> lines = file.readLines()

        int cases = lines[0] as int
        1.upto(cases) { caseIndex ->
            String line = lines[caseIndex]

            outputStream << "Case #${caseIndex}: ${convertToT9Spelling(line)}\n"
        }
    }

    String convertToT9Spelling(String text) {
        def lastKeyPressed = null
        StringBuilder stringBuilder = new StringBuilder()
        text.chars.each { c ->
            def key = mapping[c]
            if (lastKeyPressed == key){
                stringBuilder << " "
            }
            stringBuilder << key.toString()

            lastKeyPressed = key
        }
        stringBuilder.toString()
    }
}

public class Key {
    int key
    int timesPressed
    char character

    Key(Character character, int key, int timesPressed) {
        this.character = character
        this.timesPressed = timesPressed
        this.key = key
    }

    public String toString(){
        return key.toString() * timesPressed
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Key key1 = (Key) o

        if (key != key1.key) return false

        return true
    }

    int hashCode() {
        return key
    }
}
