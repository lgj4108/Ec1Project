package com.plateer.ec1.sample;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SampleTest {

    @Test
    void streamTest() {
        List<String> orgList = Arrays.asList("1", "2", "3", "4");
        List<String> test = orgList.stream().filter(p -> p.equals("1")).collect(Collectors.toList());
        log.info("orgList : {}, hash : {}, identity: {}, {}", orgList, orgList.hashCode(), System.identityHashCode(orgList));
        log.info("orgList : {}, hash : {}, identity: {}", test, test.hashCode(), System.identityHashCode(test));


        assertThat(orgList).isNotSameAs(orgList.stream().collect(Collectors.toList()));

    }
}
