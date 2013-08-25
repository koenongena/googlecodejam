import spock.lang.Specification

/**
 * User: ko
 * Date: 25/08/13
 * Time: 20:48
 */
class T9SpellingApplicationTest extends Specification {

    T9SpellingApplication underTest

    def setup(){
        underTest = new T9SpellingApplication()
    }
    def "sample parsing"(){

        given:
        ByteArrayOutputStream out = new ByteArrayOutputStream()

        when:
        underTest.parseFile(new File('sample.txt'), out)

        then:
        out.toString() == """Case #1: 44 444
Case #2: 999337777
Case #3: 333666 6660 022 2777
Case #4: 4433555 555666096667775553
"""
    }
}
