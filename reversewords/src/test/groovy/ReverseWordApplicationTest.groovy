import spock.lang.Specification

/**
 * User: ko
 * Date: 25/08/13
 * Time: 20:48
 */
class ReverseWordApplicationTest extends Specification {

    ReverseWordApplication underTest

    def setup(){
        underTest = new ReverseWordApplication()
    }
    def "sample parsing"(){

        given:
        ByteArrayOutputStream out = new ByteArrayOutputStream()

        when:
        underTest.parseFile(new File('sample.txt'), out)

        then:
        out.toString() == """Case #1: test a is this
Case #2: foobar
Case #3: base your all
"""
    }
}
