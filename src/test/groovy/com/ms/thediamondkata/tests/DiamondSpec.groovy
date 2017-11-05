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
    def "#rowChar appears in the correct row and column is diamond(#c)"() {
        given:
        def result = diamond.apply(c)
        and:
        int midPoint = result.size().intdiv(2)
        int y = rowChar - aChar
        int x = midPoint - y

        expect:
        result[y].charAt(x) == rowChar

        where:
        row << testRange.collectMany { c2 ->
            (aChar..c2).collect { new Tuple(c2, it) }
        }
        c = row[0]
        rowChar = row[1]
    }

    @Unroll
    def "the diamond is symetrical for character (#c"() {
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

    @Unroll
    def "areas arround the character #rowChar in diamond (#c) are padded"() {
        given:
        def result = diamond.apply(c)
        and:
        int midPoint = result.size().intdiv(2)
        int y = rowChar - aChar
        int x = midPoint - y

        expect:
        new StringBuilder(result[y][0..midPoint])
                .deleteCharAt(x)
                .toString()
                .every { it == '-' }

        where:
        row << testRange.collectMany { c2 ->
            (aChar..c2).collect { new Tuple(c2, it) }
        }
        c = row[0]
        rowChar = row[1]
    }
}