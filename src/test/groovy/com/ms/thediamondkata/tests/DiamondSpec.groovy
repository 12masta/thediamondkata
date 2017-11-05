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

    @Unroll
    def "the appropriate character appears in each row and column in diamond (#c)"() {
        given:
        def result = diamond.apply(c)
        and:
        int midpoint = result.size().intdiv(2)

        expect:
        for (rowChar in aChar..c) {
            int y = rowChar - aChar
            int x = midpoint - y
            assert result[y].charAt(x) == rowChar
        }

        where:
        c << testRange
    }

    @Unroll
    def "the diamond is symetrical"() {
        given:
        def result = diamond.apply(c)

        expect:
        result == result.reverse()
        and:
        result.every {
            it == it.reverse()
        }

        where:
        c << testRange
    }
}