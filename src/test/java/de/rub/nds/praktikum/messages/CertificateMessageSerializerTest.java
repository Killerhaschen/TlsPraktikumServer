package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.tls.Certificate;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe4.class)
public class CertificateMessageSerializerTest {

    @Test
    public void testSerializeBytes() throws IOException {
        org.bouncycastle.asn1.x509.Certificate cert1 = mock(org.bouncycastle.asn1.x509.Certificate.class);
        when(cert1.getEncoded()).thenReturn(new byte[]{1, 2, 3});
        org.bouncycastle.asn1.x509.Certificate cert2 = mock(org.bouncycastle.asn1.x509.Certificate.class);
        when(cert2.getEncoded()).thenReturn(new byte[]{4, 5, 6});
        //A Certificate chain consisting of only 1 certificate with the bytes 1 2 3 
        Certificate certChain1 = new Certificate(new org.bouncycastle.asn1.x509.Certificate[]{cert1});

        //A Certificate chain consisting of two certificates with the bytes 1 2 3, and 4 5 6 
        Certificate certChain2 = new Certificate(new org.bouncycastle.asn1.x509.Certificate[]{cert1, cert2});

        CertificateMessage message = new CertificateMessage(certChain1);
        CertificateMessageSerializer serializer = new CertificateMessageSerializer(message);
        assertArrayEquals("A certificate message with the certificate \"010203\"", Util.hexStringToByteArray("000000080000030102030000"), serializer.serialize());

        message = new CertificateMessage(certChain2);
        serializer = new CertificateMessageSerializer(message);
        assertArrayEquals("A certificate message with two certificates \"010203\" and \"040506\"", Util.hexStringToByteArray("0000001000000301020300000000030405060000"), serializer.serialize());
    }

    @Test
    public void testSerializeBytesGoogleCert() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(Util.hexStringToByteArray("000d9d000949308209453082082da00302010202110095f085aa076c57b7080000000014fbb5300d06092a864886f70d01010b05003042310b3009060355040613025553311e301c060355040a1315476f6f676c65205472757374205365727669636573311330110603550403130a47545320434120314f31301e170d3139303931373133333034335a170d3139313231303133333034335a3066310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e205669657731133011060355040a130a476f6f676c65204c4c433115301306035504030c0c2a2e676f6f676c652e636f6d3059301306072a8648ce3d020106082a8648ce3d03010703420004281110263ca1fb7c88b3b937f38b04fb893527936492bc1621f03cd216b9beaad43eeb25d3493aa4eda46e3a44b2f2ff771171a1223bb0b2cac8cca87befc0ada38206db308206d7300e0603551d0f0101ff04040302078030130603551d25040c300a06082b06010505070301300c0603551d130101ff04023000301d0603551d0e04160414fbea8db6a6e5c9cc013f1b578ba1ed0f5494da28301f0603551d2304183016801498d1f86e10ebcf9bec609f18901ba0eb7d09fd2b306406082b0601050507010104583056302706082b06010505073001861b687474703a2f2f6f6373702e706b692e676f6f672f677473316f31302b06082b06010505073002861f687474703a2f2f706b692e676f6f672f677372322f475453314f312e6372743082049d0603551d110482049430820490820c2a2e676f6f676c652e636f6d820d2a2e616e64726f69642e636f6d82162a2e617070656e67696e652e676f6f676c652e636f6d82122a2e636c6f75642e676f6f676c652e636f6d82182a2e63726f7764736f757263652e676f6f676c652e636f6d82062a2e672e636f820e2a2e6763702e677674322e636f6d82112a2e67637063646e2e677674312e636f6d820a2a2e67677068742e636e820e2a2e676b65636e617070732e636e82162a2e676f6f676c652d616e616c79746963732e636f6d820b2a2e676f6f676c652e6361820b2a2e676f6f676c652e636c820e2a2e676f6f676c652e636f2e696e820e2a2e676f6f676c652e636f2e6a70820e2a2e676f6f676c652e636f2e756b820f2a2e676f6f676c652e636f6d2e6172820f2a2e676f6f676c652e636f6d2e6175820f2a2e676f6f676c652e636f6d2e6272820f2a2e676f6f676c652e636f6d2e636f820f2a2e676f6f676c652e636f6d2e6d78820f2a2e676f6f676c652e636f6d2e7472820f2a2e676f6f676c652e636f6d2e766e820b2a2e676f6f676c652e6465820b2a2e676f6f676c652e6573820b2a2e676f6f676c652e6672820b2a2e676f6f676c652e6875820b2a2e676f6f676c652e6974820b2a2e676f6f676c652e6e6c820b2a2e676f6f676c652e706c820b2a2e676f6f676c652e707482122a2e676f6f676c656164617069732e636f6d820f2a2e676f6f676c65617069732e636e82112a2e676f6f676c65636e617070732e636e82142a2e676f6f676c65636f6d6d657263652e636f6d82112a2e676f6f676c65766964656f2e636f6d820c2a2e677374617469632e636e820d2a2e677374617469632e636f6d82122a2e67737461746963636e617070732e636e820a2a2e677674312e636f6d820a2a2e677674322e636f6d82142a2e6d65747269632e677374617469632e636f6d820c2a2e75726368696e2e636f6d82102a2e75726c2e676f6f676c652e636f6d82132a2e776561722e676b65636e617070732e636e82162a2e796f75747562652d6e6f636f6f6b69652e636f6d820d2a2e796f75747562652e636f6d82162a2e796f7574756265656475636174696f6e2e636f6d82112a2e796f75747562656b6964732e636f6d82072a2e79742e6265820b2a2e7974696d672e636f6d821a616e64726f69642e636c69656e74732e676f6f676c652e636f6d820b616e64726f69642e636f6d821b646576656c6f7065722e616e64726f69642e676f6f676c652e636e821c646576656c6f706572732e616e64726f69642e676f6f676c652e636e8204672e636f820867677068742e636e820c676b65636e617070732e636e8206676f6f2e676c8214676f6f676c652d616e616c79746963732e636f6d820a676f6f676c652e636f6d820f676f6f676c65636e617070732e636e8212676f6f676c65636f6d6d657263652e636f6d8218736f757263652e616e64726f69642e676f6f676c652e636e820a75726368696e2e636f6d820a7777772e676f6f2e676c8208796f7574752e6265820b796f75747562652e636f6d8214796f7574756265656475636174696f6e2e636f6d820f796f75747562656b6964732e636f6d820579742e626530210603551d20041a30183008060667810c010202300c060a2b06010401d679020503302f0603551d1f042830263024a022a020861e687474703a2f2f63726c2e706b692e676f6f672f475453314f312e63726c30820105060a2b06010401d6790204020481f60481f300f100760063f2dbcde83bcc2ccf0b728427576b33a48d61778fbd75a638b1c768544bd88d0000016d3fa20a260000040300473045022100ded5b923a8a53c7818cf49e6006948484de46e8bdc76c50af11527fee9a4d0e60220687224a82eb9922069929610ba06e0f28191f37389623e53809d22ab49121119007700747eda8331ad331091219cce254f4270c2bffd5e422008c6373579e6107bcc560000016d3fa20a4f000004030048304602210084e30d1419ca238151f9e5e44ecd37c66325c55f7b1bf5837564ea40eae45b3b02210086a81a2fcd47c8c32944aae6765df9a0ec2c9759ff74243e7f7f6d38e429ce32300d06092a864886f70d01010b0500038201010079276223bb461737c0c1841447e2f54398e556eeaccc0f09dba91d5f3f27a75970069792b7c112830f3ca74ba91d3fccfc1fe6fd0bc0ba0eb246fa4dbe05702ae0148b64342907055bedfca7adc6a2281feaa2fe946a2dc00a63e88a957dd55c52fde860bc57b69e240a2cdb6d8461fd45e21f00d826cb5548734a72731e625dfbcb50cf8820d783aecbd759e35f2b25fce147bdd9399c5123d135e86246cc460b6c62e52d0d77b1c903d8bd7710a55863a8c8881e9b525c38ec2f690b5012edde2162306c34e1306a20d91bce6a6633ed988bd865d802529cd853a8bd2658416e7217891d6046bc04bef4a3e365c4831165534484fa579389834a6f8acf993200044e3082044a30820332a003020102020d01e3b49aa18d8aa981256950b8300d06092a864886f70d01010b0500304c3120301e060355040b1317476c6f62616c5369676e20526f6f74204341202d20523231133011060355040a130a476c6f62616c5369676e311330110603550403130a476c6f62616c5369676e301e170d3137303631353030303034325a170d3231313231353030303034325a3042310b3009060355040613025553311e301c060355040a1315476f6f676c65205472757374205365727669636573311330110603550403130a47545320434120314f3130820122300d06092a864886f70d01010105000382010f003082010a0282010100d018cf45d48bcdd39ce440ef7eb4dd69211bc9cf3c8e4c75b90f3119843d9e3c29ef500d10936f0580809f2aa0bd124b02e13d9f581624fe309f0b747755931d4bf74de1928210f651ac0cc3b222940f346b981049e70b9d8339dd20c61c2defd1186165e7238320a82312ffd2247fd42fe7446a5b4dd75066b0af9e426305fbe01cc46361af9f6a33ff6297bd48d9d37c1467dc75dc2e69e8f86d7869d0b71005b8f131c23b24fd1a3374f823e0ec6b198a16c6e3cda4cd0bdbb3a4596038883bad1db9c68ca7531bfcbcd9a4abbcdd3c61d7931598ee81bd8fe264472040064ed7ac97e8b9c05912a1492523e4ed70342ca5b4637cf9a33d83d1cd6d24ac070203010001a38201333082012f300e0603551d0f0101ff040403020186301d0603551d250416301406082b0601050507030106082b0601050507030230120603551d130101ff040830060101ff020100301d0603551d0e0416041498d1f86e10ebcf9bec609f18901ba0eb7d09fd2b301f0603551d230418301680149be20757671c1ec06a06de59b49a2ddfdc19862e303506082b0601050507010104293027302506082b060105050730018619687474703a2f2f6f6373702e706b692e676f6f672f6773723230320603551d1f042b30293027a025a0238621687474703a2f2f63726c2e706b692e676f6f672f677372322f677372322e63726c303f0603551d20043830363034060667810c010202302a302806082b06010505070201161c68747470733a2f2f706b692e676f6f672f7265706f7369746f72792f300d06092a864886f70d01010b050003820101001a803e3679fbf32ea946377d5e541635aec74e0899febdd13469265266073d0aba49cb62f4f11a8efc114f68964c742bd367deb2a3aa058d844d4c20650fa596da0d16f86c3bdb6f0423886b3a6cc160bd689f718eee2d583407f0d554e98659fd7b5e0d2194f58cc9a8f8d8f2adcc0f1af39aa7a90427f9a3c9b0ff02786b61bac7352be856fa4fc31c0cedb63cb44beaedcce13cecdc0d8cd63e9bca42588bcc16211740bca2d666efdac4155bcd89aa9b0926e732d20d6e6720025b10b090099c0c1f9eadd83beaa1fc6ce8105c085219512a71bbac7ab5dd15ed2bc9082a2c8ab4a621ab63ffd7524950d089b7adf2affb50ae2fe1950df346ad9d9cf5ca"));
        Certificate parsedCert = Certificate.parse(stream);
        CertificateMessage message = new CertificateMessage(parsedCert);
        CertificateMessageSerializer serializer = new CertificateMessageSerializer(message);
        assertArrayEquals("A certificate message with the google certifciate chain (2 certificates)", Util.hexStringToByteArray("00000da1000949308209453082082da00302010202110095f085aa076c57b7080000000014fbb5300d06092a864886f70d01010b05003042310b3009060355040613025553311e301c060355040a1315476f6f676c65205472757374205365727669636573311330110603550403130a47545320434120314f31301e170d3139303931373133333034335a170d3139313231303133333034335a3066310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e205669657731133011060355040a130a476f6f676c65204c4c433115301306035504030c0c2a2e676f6f676c652e636f6d3059301306072a8648ce3d020106082a8648ce3d03010703420004281110263ca1fb7c88b3b937f38b04fb893527936492bc1621f03cd216b9beaad43eeb25d3493aa4eda46e3a44b2f2ff771171a1223bb0b2cac8cca87befc0ada38206db308206d7300e0603551d0f0101ff04040302078030130603551d25040c300a06082b06010505070301300c0603551d130101ff04023000301d0603551d0e04160414fbea8db6a6e5c9cc013f1b578ba1ed0f5494da28301f0603551d2304183016801498d1f86e10ebcf9bec609f18901ba0eb7d09fd2b306406082b0601050507010104583056302706082b06010505073001861b687474703a2f2f6f6373702e706b692e676f6f672f677473316f31302b06082b06010505073002861f687474703a2f2f706b692e676f6f672f677372322f475453314f312e6372743082049d0603551d110482049430820490820c2a2e676f6f676c652e636f6d820d2a2e616e64726f69642e636f6d82162a2e617070656e67696e652e676f6f676c652e636f6d82122a2e636c6f75642e676f6f676c652e636f6d82182a2e63726f7764736f757263652e676f6f676c652e636f6d82062a2e672e636f820e2a2e6763702e677674322e636f6d82112a2e67637063646e2e677674312e636f6d820a2a2e67677068742e636e820e2a2e676b65636e617070732e636e82162a2e676f6f676c652d616e616c79746963732e636f6d820b2a2e676f6f676c652e6361820b2a2e676f6f676c652e636c820e2a2e676f6f676c652e636f2e696e820e2a2e676f6f676c652e636f2e6a70820e2a2e676f6f676c652e636f2e756b820f2a2e676f6f676c652e636f6d2e6172820f2a2e676f6f676c652e636f6d2e6175820f2a2e676f6f676c652e636f6d2e6272820f2a2e676f6f676c652e636f6d2e636f820f2a2e676f6f676c652e636f6d2e6d78820f2a2e676f6f676c652e636f6d2e7472820f2a2e676f6f676c652e636f6d2e766e820b2a2e676f6f676c652e6465820b2a2e676f6f676c652e6573820b2a2e676f6f676c652e6672820b2a2e676f6f676c652e6875820b2a2e676f6f676c652e6974820b2a2e676f6f676c652e6e6c820b2a2e676f6f676c652e706c820b2a2e676f6f676c652e707482122a2e676f6f676c656164617069732e636f6d820f2a2e676f6f676c65617069732e636e82112a2e676f6f676c65636e617070732e636e82142a2e676f6f676c65636f6d6d657263652e636f6d82112a2e676f6f676c65766964656f2e636f6d820c2a2e677374617469632e636e820d2a2e677374617469632e636f6d82122a2e67737461746963636e617070732e636e820a2a2e677674312e636f6d820a2a2e677674322e636f6d82142a2e6d65747269632e677374617469632e636f6d820c2a2e75726368696e2e636f6d82102a2e75726c2e676f6f676c652e636f6d82132a2e776561722e676b65636e617070732e636e82162a2e796f75747562652d6e6f636f6f6b69652e636f6d820d2a2e796f75747562652e636f6d82162a2e796f7574756265656475636174696f6e2e636f6d82112a2e796f75747562656b6964732e636f6d82072a2e79742e6265820b2a2e7974696d672e636f6d821a616e64726f69642e636c69656e74732e676f6f676c652e636f6d820b616e64726f69642e636f6d821b646576656c6f7065722e616e64726f69642e676f6f676c652e636e821c646576656c6f706572732e616e64726f69642e676f6f676c652e636e8204672e636f820867677068742e636e820c676b65636e617070732e636e8206676f6f2e676c8214676f6f676c652d616e616c79746963732e636f6d820a676f6f676c652e636f6d820f676f6f676c65636e617070732e636e8212676f6f676c65636f6d6d657263652e636f6d8218736f757263652e616e64726f69642e676f6f676c652e636e820a75726368696e2e636f6d820a7777772e676f6f2e676c8208796f7574752e6265820b796f75747562652e636f6d8214796f7574756265656475636174696f6e2e636f6d820f796f75747562656b6964732e636f6d820579742e626530210603551d20041a30183008060667810c010202300c060a2b06010401d679020503302f0603551d1f042830263024a022a020861e687474703a2f2f63726c2e706b692e676f6f672f475453314f312e63726c30820105060a2b06010401d6790204020481f60481f300f100760063f2dbcde83bcc2ccf0b728427576b33a48d61778fbd75a638b1c768544bd88d0000016d3fa20a260000040300473045022100ded5b923a8a53c7818cf49e6006948484de46e8bdc76c50af11527fee9a4d0e60220687224a82eb9922069929610ba06e0f28191f37389623e53809d22ab49121119007700747eda8331ad331091219cce254f4270c2bffd5e422008c6373579e6107bcc560000016d3fa20a4f000004030048304602210084e30d1419ca238151f9e5e44ecd37c66325c55f7b1bf5837564ea40eae45b3b02210086a81a2fcd47c8c32944aae6765df9a0ec2c9759ff74243e7f7f6d38e429ce32300d06092a864886f70d01010b0500038201010079276223bb461737c0c1841447e2f54398e556eeaccc0f09dba91d5f3f27a75970069792b7c112830f3ca74ba91d3fccfc1fe6fd0bc0ba0eb246fa4dbe05702ae0148b64342907055bedfca7adc6a2281feaa2fe946a2dc00a63e88a957dd55c52fde860bc57b69e240a2cdb6d8461fd45e21f00d826cb5548734a72731e625dfbcb50cf8820d783aecbd759e35f2b25fce147bdd9399c5123d135e86246cc460b6c62e52d0d77b1c903d8bd7710a55863a8c8881e9b525c38ec2f690b5012edde2162306c34e1306a20d91bce6a6633ed988bd865d802529cd853a8bd2658416e7217891d6046bc04bef4a3e365c4831165534484fa579389834a6f8acf9932000000044e3082044a30820332a003020102020d01e3b49aa18d8aa981256950b8300d06092a864886f70d01010b0500304c3120301e060355040b1317476c6f62616c5369676e20526f6f74204341202d20523231133011060355040a130a476c6f62616c5369676e311330110603550403130a476c6f62616c5369676e301e170d3137303631353030303034325a170d3231313231353030303034325a3042310b3009060355040613025553311e301c060355040a1315476f6f676c65205472757374205365727669636573311330110603550403130a47545320434120314f3130820122300d06092a864886f70d01010105000382010f003082010a0282010100d018cf45d48bcdd39ce440ef7eb4dd69211bc9cf3c8e4c75b90f3119843d9e3c29ef500d10936f0580809f2aa0bd124b02e13d9f581624fe309f0b747755931d4bf74de1928210f651ac0cc3b222940f346b981049e70b9d8339dd20c61c2defd1186165e7238320a82312ffd2247fd42fe7446a5b4dd75066b0af9e426305fbe01cc46361af9f6a33ff6297bd48d9d37c1467dc75dc2e69e8f86d7869d0b71005b8f131c23b24fd1a3374f823e0ec6b198a16c6e3cda4cd0bdbb3a4596038883bad1db9c68ca7531bfcbcd9a4abbcdd3c61d7931598ee81bd8fe264472040064ed7ac97e8b9c05912a1492523e4ed70342ca5b4637cf9a33d83d1cd6d24ac070203010001a38201333082012f300e0603551d0f0101ff040403020186301d0603551d250416301406082b0601050507030106082b0601050507030230120603551d130101ff040830060101ff020100301d0603551d0e0416041498d1f86e10ebcf9bec609f18901ba0eb7d09fd2b301f0603551d230418301680149be20757671c1ec06a06de59b49a2ddfdc19862e303506082b0601050507010104293027302506082b060105050730018619687474703a2f2f6f6373702e706b692e676f6f672f6773723230320603551d1f042b30293027a025a0238621687474703a2f2f63726c2e706b692e676f6f672f677372322f677372322e63726c303f0603551d20043830363034060667810c010202302a302806082b06010505070201161c68747470733a2f2f706b692e676f6f672f7265706f7369746f72792f300d06092a864886f70d01010b050003820101001a803e3679fbf32ea946377d5e541635aec74e0899febdd13469265266073d0aba49cb62f4f11a8efc114f68964c742bd367deb2a3aa058d844d4c20650fa596da0d16f86c3bdb6f0423886b3a6cc160bd689f718eee2d583407f0d554e98659fd7b5e0d2194f58cc9a8f8d8f2adcc0f1af39aa7a90427f9a3c9b0ff02786b61bac7352be856fa4fc31c0cedb63cb44beaedcce13cecdc0d8cd63e9bca42588bcc16211740bca2d666efdac4155bcd89aa9b0926e732d20d6e6720025b10b090099c0c1f9eadd83beaa1fc6ce8105c085219512a71bbac7ab5dd15ed2bc9082a2c8ab4a621ab63ffd7524950d089b7adf2affb50ae2fe1950df346ad9d9cf5ca0000"), serializer.serialize());
    }

}