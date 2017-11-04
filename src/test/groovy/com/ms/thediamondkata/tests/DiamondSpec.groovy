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
    char zChar = 'Z'
    @Shared
    Range<Character> validRange = aChar..zChar


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

    def "The diaomnd of A is 'A'"(){
        expect:
        diamond.apply(aChar) == ["A"]
    }
}
