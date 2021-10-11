package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {

    @InjectMocks
    private CreateAccountTypeFlowImpl flow;
    @Mock
    private AccountTypeTranslator translator;

    @Before // runs before every test is performed
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        when(translator.create(any(AccountTypeDto.class))).thenReturn(new AccountTypeDto());
        AccountTypeDto result = flow.create(new AccountTypeDto());
        assertNotNull(result);
        verify(translator,atLeastOnce()).create(any(AccountTypeDto.class));
    }

    @Test
    public void equals() {
        AccountTypeDto accountTypeDto = new AccountTypeDto(null,null,LocalDate.now());
        when(translator.create(any(AccountTypeDto.class))).then(returnsFirstArg());
        AccountTypeDto result = flow.create(new AccountTypeDto());
        assertNotNull(result);
        assertEquals(LocalDate.now(),result.getCreationDate());
        verify(translator,times(1)).create(eq(accountTypeDto));
    }

    @Test
    public void testExceptions() {
        when(translator.getAccountTypeDtoByMnemonic(anyString())).thenThrow(new RuntimeException());
        try {
            AccountTypeDto accountTypeDto = translator.getAccountTypeDtoByMnemonic("");
            flow.create(new AccountTypeDto());
            fail("Should throw an exception");
        } catch (Exception e) {
            // throw new Exception("Something went wrong",e);
        }
        verify(translator,times(1)).getAccountTypeDtoByMnemonic(anyString());
        verify(translator,never()).create(any(AccountTypeDto.class));
    }
}