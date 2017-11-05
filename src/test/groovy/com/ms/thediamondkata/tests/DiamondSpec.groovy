package com.ms.thediamondkata.tests

import com.ms.thediamondkata.Diamond
import spock.genesis.Gen
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class DiamondSpec extends Specification {

    @Subject
    def diamond = new Diamond()

    @Shared
    char aChar = 'A'
    @Shared
    char bChar = 'B'
    @Shared
    char zChar = 'Z'
    @Shared
    Range<Character> validRange = aChar..zChar
    @Shared
    Range<Character> testRange = bChar..zChar

    @Unroll("rejects '#c'")
    def "rejects characters outside the range A-Z"() {
        when:
        diamond.apply(c)

        then:
        thrown IllegalArgumentException

        where:
        c << Gen.character
                .filter { !validRange.contains(it) }
                .take(50)
    }

    def "The diaomnd of A is 'A'"() {
        expect:
        diamond.apply(aChar) == ["A"]
    }

    @Unroll
    def "diamond (#c) is square"() {
        given:
        def result = diamond.apply(c)

        expect:
        result.every {
            it.length() == result.size()
        }

        where:
        c << testRange
    }

    @Unroll("diamond (#c) should have #expectedHeight rows")
    def "a diamond's height is determined by the character argument"() {
        given:
        def result = diamond.apply(c)

        expect:
        result.size() == expectedHeight

        where:
        c << testRange

        expectedHeight = ((c - aChar) * 2) + 1
    }

    @Unroll
    def "the appropriate character appears in each row and column in diamond (#c)"() {
        given:
        def result = diamond.apply(c)

        expect:
        int lastIndex = result.size() - 1
        int midPoint = result.size().intdiv(2)

        for (rowChar in aChar..c) {
            int rowIndex = rowChar - aChar
            def topLeft = [x: midPoint - rowIndex, y: rowIndex]
            def topRight = [x: lastIndex - topLeft.x, y: rowIndex]
            def bottomLeft = [x: topLeft.x, y: lastIndex - rowIndex]
            def bottomRight = [x: topRight.x, y: bottomLeft.y]

            assert result[topLeft.y].charAt(topLeft.x) == rowChar
            assert result[topRight.y].charAt(topRight.x) == rowChar
            assert result[bottomLeft.y].charAt(bottomLeft.x) == rowChar
            assert result[bottomRight.y].charAt(bottomRight.x) == rowChar
        }

        where:
        c << testRange
    }
}