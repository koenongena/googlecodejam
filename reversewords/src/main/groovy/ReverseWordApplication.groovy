/**
 * User: ko
 * Date: 25/08/13
 * Time: 20:41
 */
class ReverseWordApplication {

    public static void main(String[] args) {
        new ReverseWordApplication().parseFile(new File("B-large-practice.in"), new FileOutputStream("B-large-practice.out"))
    }

    void parseFile(File file, OutputStream outputStream) {
        List<String> lines = file.readLines()

        int cases = lines[0] as int
        1.upto(cases) { caseIndex ->
             String line = lines[caseIndex]
            def words = line.split(" ")

            outputStream << "Case #${caseIndex}: ${words.reverse().join(" ")}\n"
        }
    }
}
