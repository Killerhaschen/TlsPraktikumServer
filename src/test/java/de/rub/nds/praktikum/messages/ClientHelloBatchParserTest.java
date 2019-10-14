package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.util.Util;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe2.class)
@RunWith(Parameterized.class)
public class ClientHelloBatchParserTest {

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays
                .asList(new Object[][]{
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A normal valid ClientHello"
            },
            {
                "03011ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with TLS 1.0 as a version"
            },
            {
                "AAAA1ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with an unspecified version (AAAA)"
            },
            {
                "03041ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with TLS 1.3 as a version (0304)"
            },
            {
                "0305b2979107cab5e3e20a58ca4ff1879426918cfcbda36ef63a52fbe673565935c220779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with a different random value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520a3790fa587bf9e745b9735f4f75da583fbdab841fe223ce0d902066e457694f5003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with a different session id value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5521FFFF9b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                true,
                "A client Hello with a bigger session id value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db551F9b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with a smaller session id value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5500003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with an empty session id value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db55FF779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                true,
                "A client Hello with an invalid session id value"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003F130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                true,
                "A client Hello with an invalid ciphersuite length field"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e150000010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with no ciphersuites"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003eFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with unspecified ciphersuites"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e1500021301010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with only one ciphersuite"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff02000100a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello with multiple compression algorithms"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff0000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                false,
                "A client Hello without compression algorithms"
            },
            {
                "0100012e03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a80000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                true,
                "A client Hello with too big extension length field"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a60000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c",
                true,
                "A client Hello with too small extension length field"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff01000000",
                false,
                "A client Hello without extensions"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000020002",
                true,
                "A client Hello with a half extension (only type)"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff0100000400020002",
                true,
                "A client Hello with a half extension (only type and length)"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff0100000101",
                true,
                "A client Hello with a half extension (only half type)"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff01000004000200",
                true,
                "A client Hello with a half extension (only half length)"
            },
            {
                "03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682cAAAA",
                true,
                "A client Hello with garbage bytes at the end"
            },});
    }
    private final ClientHelloParser parser;

    private final byte[] message;

    private final boolean expectedException;

    private final String description;

    public ClientHelloBatchParserTest(String hexMessage, boolean expectedException, String description) {
        this.message = Util.hexStringToByteArray(hexMessage);
        this.expectedException = expectedException;
        this.description = description;
        parser = new ClientHelloParser(this.message);

    }

    @Test
    public void testParseShouldWork() {
        try {
            parser.parse();
            if (expectedException) {
                fail("We expected an exception for:" + description);
            }
        } catch (ParserException E) {
            if (!expectedException) {
                fail("We did not expect an exception for:" + description);
            }
        }
    }

}