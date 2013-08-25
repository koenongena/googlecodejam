import spock.lang.Specification
import spock.lang.Unroll

/**
 * User: ko
 * Date: 24/08/13
 * Time: 21:08
 */
class StoreCreditApplicationTest extends Specification {
    StoreCreditApplication underTest

    def setup() {
        underTest = new StoreCreditApplication()

    }

    @Unroll
    def "case 1"() {
        given:
        def credits = StoreCredit.createListFrom(entries)

        when:
        def result = underTest.calculateIndex(credits, amount)

        then:
        result.index1 == index1
        result.index2 == index2

        where:
        amount | entries                       | index1 | index2
        100    | [5, 25, 75]                   | 2      | 3
        200    | [150, 24, 79, 50, 88, 345, 3] | 1      | 4
        8      | [2, 1, 9, 4, 4, 56, 90, 3]    | 4      | 5

    }

    def "test read file"(){
        given:
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()

        when:
        underTest.parseFile(new File('sample1.txt'), outputStream)

        then:
        outputStream.toString() == """Case #1: 2 3
Case #2: 1 4
Case #3: 4 5
"""

    }

}
