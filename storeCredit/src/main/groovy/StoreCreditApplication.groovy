/**
 * User: ko
 * Date: 24/08/13
 * Time: 20:54
 */
class StoreCreditApplication {

    public static void main(String[] args) {
        new StoreCreditApplication().parseFile(new File("A-large-practice.in"),
                new FileOutputStream("A-large-practice.out"))
    }

    def parseFile(File file, OutputStream out){
        println "parsing ${file.absolutePath}"

        List<String> lines = file.readLines()

        int numberOfCases = lines[0] as int

        0.upto(numberOfCases - 1) { int caseIndex ->
            long amount = lines[1 + (caseIndex*3)] as long
            long numberOfCredits  = lines[2 + (caseIndex*3)] as long

            List<StoreCredit> credits = createCredits(lines[3 + (caseIndex*3)].split(" "))
            def result = calculateIndex(credits, amount)
            out << "Case #${caseIndex + 1}: ${result.index1} ${result.index2}"
            out << "\n"
        }
    }

    def calculateIndex(List<StoreCredit> storeCredits, long amount) {
        def creditsSorted = storeCredits.sort()
        def lowerIndex = 0
        def upperIndex = creditsSorted.size() - 1

        while (lowerIndex < upperIndex){
            def maxCredit = creditsSorted[upperIndex]
            def minCredit = creditsSorted[lowerIndex]
            if (maxCredit + minCredit > amount) {
                upperIndex--
            } else if (maxCredit + minCredit < amount){
                lowerIndex++
            } else{
                return [index1: Math.min(minCredit.index, maxCredit.index),
                        index2: Math.max(minCredit.index, maxCredit.index)]
            }
        }
        throw new RuntimeException("No solution found")
    }

    static List<StoreCredit> createCredits(String[] credits) {
        def index = 1
        return credits.collect() { credit ->
            new StoreCredit(index: index++, value: credit as long)
        }
    }
}

class StoreCredit implements Comparable<StoreCredit> {
    long value
    int index

    static def createListFrom(List<Long> values) {
        int index = 1
        return values.collect {
               new StoreCredit(index: index++, value: it)
        }
    }

    @Override
    int compareTo(StoreCredit o) {
        return value.compareTo(o.value)
    }

    long plus(StoreCredit c){
        return value + c.value
    }
}
