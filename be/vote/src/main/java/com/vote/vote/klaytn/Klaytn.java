package com.vote.vote.klaytn;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.klaytn.caver.Caver;
import com.klaytn.caver.crpyto.KlayCredentials;
import com.klaytn.caver.fee.FeePayerManager;
import com.klaytn.caver.methods.response.KlayTransactionReceipt;
import com.klaytn.caver.tx.ValueTransfer;
import com.klaytn.caver.tx.gas.DefaultGasProvider;
import com.klaytn.caver.tx.manager.TransactionManager;
import com.klaytn.caver.tx.model.SmartContractDeployTransaction;
import com.klaytn.caver.tx.model.SmartContractExecutionTransaction;
import com.klaytn.caver.utils.ChainId;
import com.klaytn.caver.utils.Convert;
import com.vote.vote.klaytn.smartContract.Test;
import com.vote.vote.klaytn.smartContract.Test6;

// import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.json.simple.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Keys;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.utils.Numeric;

//import org.web3j.rlp.*;
public class Klaytn {
	public JSONObject createKey() throws Exception{ // 키 쌍으로 어카운트 생성
		KlayCredentials credentials =        
			    KlayCredentials.create(Keys.createEcKeyPair());
		// 계정 생성
		String privateKey = // 비밀키
			Numeric.toHexStringWithPrefix(
				credentials.getEcKeyPair().getPrivateKey());
		
		
		String address = credentials.getAddress(); // 어드레스값
			
			// 생성한 어카운트에는 
			//KlayCredentials credentials = KlayCredentials.create(<private key>); 이걸로 접근
			// System.out.println("privateKey: "+privateKey);
			// System.out.println("address: "+address);

		JSONObject result = new JSONObject();
		// result.putOpt("privateKey")
		result.put("privateKey",privateKey);
		result.put("address",address);
		return result;
	}
	
	public void sendKlay() throws Exception {// 클레이 전송
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		
		KlayCredentials credentials = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");
		
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
	    = ValueTransfer.sendFunds(
	                caver, 
	                credentials, 
	                "0x4222a646e7920fd79352e184f6cee28a5afd2ddb",
	                BigDecimal.valueOf(1), 
	                Convert.Unit.KLAY,  
	                new DefaultGasProvider().getGasLimit()).send();
		
		String txHash = transactionReceipt.getTransactionHash();
		System.out.println("txHash : "+txHash);
	}
	//1)caver, (2)klayCredentials, (3)toAddress, (4)value, (5)unit,(6)gasLimit을 필요로 하며, 리턴값으로는 TransactionReceipt를 반환합니다.
	//(1)caver는 바오밥이나
//	메인넷체인과 연결하는 역할을 하며, 
	//(2)klayCredentials는 보내는 사람의 계정을 의미합니다. 
	//(3)toAddress는 Klay를 받는 사람의 EOA 주소, 
	//(4)value는 보내는 클레이의 값, 
	//(5)unit은 Klay의 단위, 
	//(6)gasLimit은 Klay를 전송할 때 드는 수수료(가스비)의 최대값을 의미합니다.
	
	
	// 스마트 컨트렉트 
	public void disploy() throws Exception{
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		
		KlayCredentials credentials = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");
		
		Test eeToken = Test.deploy(
			caver,
			credentials,
			ChainId.BAOBAB_TESTNET,
			new DefaultGasProvider()).send();
		String deployedContractAddress = eeToken.getContractAddress();
		
		System.out.println("deployedContractAddress : " + deployedContractAddress);
		
		
	}
	public void smart_contract_ex() throws Exception{// 배포된 컨트렉트로 클레이 전송
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		
		KlayCredentials credentials = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		
//		0xcc1b2b7db95514cced8b7dcb169470eeda34ca76    -  대납
		Test eeToken = Test.load(
				"0x41119306f23ecca50a50e0c7f75c01a9645fcb5f",// 콘트렉트 adress 
				caver, 
				credentials, 
				ChainId.BAOBAB_TESTNET,
				new DefaultGasProvider());
		
		KlayTransactionReceipt.TransactionReceipt transactionReceipt = 
				eeToken.transfer(BigInteger.ZERO).send();
		String txHash = transactionReceipt.getTransactionHash();
		System.out.println("After"+eeToken.getBalance());
		
		System.out.println("txHash : " + txHash);
	}
	
	
	// 수수료 대납
	public void test_pee() throws Exception{ // 수수료 스마트 컨트렉트로 클레이 전송 
		// Caver caver = Caver.build(Caver.BAOBAB_URL);
		// KlayCredentials sender = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");
		
//		Function function = new Function(
//				Test2.FUNC_TRANSFER,  // FUNC_TRANSFER = "transfer"
//		        Arrays.asList(
//		        		new Uint256(BigInteger.ZERO)
//		        		),  // inputParameters
//		        Collections.emptyList()  // outputParameters          
//		);
//		String data = FunctionEncoder.encode(function);
		
//		String str="1212";
//
//		byte[] byteArray=str.getBytes();
//		
//		String strYes = new String(byteArray);
//		System.out.println(strYes);
		
		// JSONObject json = new JSONObject();
		// json.put("title", "first Vote");
		// json.put("vote", "1");
		// byte[] sendData = json.toString().getBytes("utf-8");
		
		// JSONObject testV= new JSONObject(new String(sendData));
		// System.out.println(testV);
		
// 		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
//                 .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
// 		SmartContractExecutionTransaction smartContractExecutionTransaction
// 			= SmartContractExecutionTransaction.create(
// 				sender.getAddress(),//만드는 User 의 address
// 				"0xbbbb511bd256fa8b7bc8235841ca1d7e162816fa",// 대납 콘트렉트 주소                                      
// 		        BigInteger.ZERO,
// //		        Numeric.hexStringToByteArray(data),       
// 		        sendData, //전송할 데이터
// //		        new DefaultGasProvider().getGasLimit(Test.FUNC_TRANSFER)
// 		        BigInteger.valueOf(100_000)
// 		);
// 		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
// 		String rawTransaction
// 		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
// 		KlayCredentials feePayer = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");// 수수료 부담하는 사람의 private key
// 		FeePayerManager feePayerManager
// 		        = new FeePayerManager.Builder(caver, feePayer)
// 		        		.build();
// //		                .setChainId(ChainId.BAOBAB_TESTNET).build();
// 		//Sync : FeePayer ExecuteTx
// 		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
// 			= feePayerManager.executeTransaction(rawTransaction);
// 		String errorMessage = transactionReceipt.getErrorMessage();
// 		String txHash = transactionReceipt.getTransactionHash();
		
// 		System.out.println("txHash: "+txHash);
// 		System.out.println("errorMessage : "+ errorMessage);
	}
	
	// 수수료 대납 
	
	
	// 수수료 대납 컨트랙트 배포
	public void test_pee_deploy() throws Exception{ // 수수료 대납 컨트렉트 배포
		
		String sendUser = "0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0"; 

		String feeUser = "0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2"; 
		// 수수료 부담하는 사람  Private Key
		
//		String data = "60806040526000805534801561001457600080fd5b5060e8806100236000396000f3fe6080604052348015600f57600080fd5b5060043610603c5760003560e01c806306661abd14604157806342cbb15c14605d578063d14e62b8146079575b600080fd5b604760a4565b6040518082815260200191505060405180910390f35b606360aa565b6040518082815260200191505060405180910390f35b60a260048036036020811015608d57600080fd5b810190808035906020019092919050505060b2565b005b60005481565b600043905090565b806000819055505056fea165627a7a7230582087453d981a85f80c5262508e1fe5abe94dc38b1167c49b6e3477b74293e9e7000029";
		String data = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610251806100606000396000f3fe60806040526004361061003f5760003560e01c806312065fe01461004157806312514bba1461006c5780638da5cb5b146100bf578063d0e30db014610116575b005b34801561004d57600080fd5b50610056610120565b6040518082815260200191505060405180910390f35b34801561007857600080fd5b506100a56004803603602081101561008f57600080fd5b810190808035906020019092919050505061013f565b604051808215151515815260200191505060405180910390f35b3480156100cb57600080fd5b506100d46101a5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61011e6101ca565b005b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008161014a610120565b101561015557600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f1935050505015801561019b573d6000803e3d6000fd5b5060019050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461022357600080fd5b56fea165627a7a72305820987acd37fa17a8ec44d7f750065fa6b1810da4a9ebbef8fd8e3640656468b7690029";
//		String data= "";

		
		// caver & 계정 로드
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		KlayCredentials sender = KlayCredentials.create(sendUser);
		
		// SmartContracdeployTransaction 생성
		SmartContractDeployTransaction smartContractDeployTransaction = SmartContractDeployTransaction.create(
				sender.getAddress(), // 컨트렉트를 배포하고자 하는 계정의 주소
				BigInteger.ZERO, 
				Numeric.hexStringToByteArray(data), 
				new DefaultGasProvider().getGasLimit(), 
				BigInteger.ZERO);
		
		// sender의 계정으로 TransactionManager 인스턴스 생성
		TransactionManager txManager
			= new TransactionManager
				.Builder(caver, sender)
				.setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		String senderRawTransaction
			= txManager.sign(smartContractDeployTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create(feeUser);
		
		FeePayerManager feePayerManager
			= new FeePayerManager.Builder(caver,  feePayer)
				.setChainId(ChainId.BAOBAB_TESTNET).build();
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(senderRawTransaction);
		
		String txHash = transactionReceipt.getTransactionHash(); 
		String deployedContractAddress = transactionReceipt.getContractAddress();
		String errorMessage = transactionReceipt.getErrorMessage();
		
		System.out.println("txHash : "+txHash);
		System.out.println("deployedContractAddress : " + deployedContractAddress);
		System.out.println("errorMessage : " + errorMessage);
		
	}
	
	
	// 수수료 대납 컨트랙트 배포  솔리디티 (int  getter setter ) 
	public JSONObject klaytnDeploy() throws Exception{ // 테스트 solidity 저장

		
		String sendUser = "0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0"; 

		String feeUser = "0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2"; 
		// 수수료 부담하는 사람  Private Key

		String data= "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060018190555061047d806100686000396000f3fe6080604052600436106100705760003560e01c80638da5cb5b1161004e5780638da5cb5b1461015c578063a87d942c146101b3578063b971691b146101de578063d0e30db01461023b57610070565b806312065fe01461007257806312514bba1461009d5780633bc5de30146100f0575b005b34801561007e57600080fd5b50610087610245565b6040518082815260200191505060405180910390f35b3480156100a957600080fd5b506100d6600480360360208110156100c057600080fd5b8101908080359060200190929190505050610264565b604051808215151515815260200191505060405180910390f35b3480156100fc57600080fd5b506101056102ca565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561014857808201518184015260208101905061012d565b505050509050019250505060405180910390f35b34801561016857600080fd5b50610171610322565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101bf57600080fd5b506101c8610347565b6040518082815260200191505060405180910390f35b3480156101ea57600080fd5b506102216004803603604081101561020157600080fd5b810190808035906020019092919080359060200190929190505050610351565b604051808215151515815260200191505060405180910390f35b6102436103f6565b005b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008161026f610245565b101561027a57600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f193505050501580156102c0573d6000803e3d6000fd5b5060019050919050565b6060600280548060200260200160405190810160405280929190818152602001828054801561031857602002820191906000526020600020905b815481526020019060010190808311610304575b5050505050905090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600154905090565b60008261035c610245565b101561036757600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051600060405180830381858888f193505050501580156103ad573d6000803e3d6000fd5b5060028290806001815401808255809150509060018203906000526020600020016000909192909190915055506001600081548092919060010191905055506001905092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461044f57600080fd5b56fea165627a7a723058204babebd74b60facf6d39a6bced9e9c5ad53a98d533fabb90acaa2415f6e280fc0029";


		
		// caver & 계정 로드
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		KlayCredentials sender = KlayCredentials.create(sendUser);
		
		// SmartContracdeployTransaction 생성
		SmartContractDeployTransaction smartContractDeployTransaction = SmartContractDeployTransaction.create(
				sender.getAddress(), // 컨트렉트를 배포하고자 하는 계정의 주소
				BigInteger.ZERO, 
				Numeric.hexStringToByteArray(data), 
				new DefaultGasProvider().getGasLimit(), 
				BigInteger.ZERO);
		
		// sender의 계정으로 TransactionManager 인스턴스 생성
		TransactionManager txManager
			= new TransactionManager
				.Builder(caver, sender)
				.setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		String senderRawTransaction
			= txManager.sign(smartContractDeployTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create(feeUser);
		
		FeePayerManager feePayerManager
			= new FeePayerManager.Builder(caver,  feePayer)
				.setChainId(ChainId.BAOBAB_TESTNET).build();
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(senderRawTransaction);
		
		String txHash = transactionReceipt.getTransactionHash(); 
		String deployedContractAddress = transactionReceipt.getContractAddress();
		String errorMessage = transactionReceipt.getErrorMessage();
		
		System.out.println("txHash : "+txHash);
		System.out.println("deployedContractAddress : " + deployedContractAddress);
		System.out.println("errorMessage : " + errorMessage);

		JSONObject result = new JSONObject();
		result.put("address",deployedContractAddress);
		return result;
		
	}
	public JSONObject klaytnSend(String address, int select) throws Exception{ // 수수료 스마트 컨트렉트로 클레이 전송 // 솔리디티 파일로 데이터 전송
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		
		String str="1";
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
				Test6.FUNC_TRANSFERWITHDATA,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(BigInteger.ZERO),
		        		new Uint256(select)
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}




	public JSONObject load(String address) throws Exception{// 배포된 스마트 컨트렉트의 uint List 출력, count 출력 
			Caver caver = Caver.build(Caver.BAOBAB_URL);
			
			KlayCredentials credentials = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
			
//			0xcc1b2b7db95514cced8b7dcb169470eeda34ca76    -  대납
			Test6 eeToken = Test6.load(
//					"0x7b6c602b7e8dde60f16ec0684421d69868215f25",// 콘트렉트 adress 
//					"0x3970bec0c994b437d4bb2ccc277e965ce24f9ff6",
//					"0xb9be5c12aaf13a18e866883fccfa3327283d4891",
					// "0x3caef473574f6a9de0501d3073c5300e5a05ed1d",
					address,
					caver, 
					credentials, 
					ChainId.BAOBAB_TESTNET,
					new DefaultGasProvider());
				
			RemoteCall<List> remoteCall = eeToken.getData();

			System.out.println("Vote List : "+remoteCall.send());
			
			System.out.println("Count : "+eeToken.getCount().send());

			JSONObject json = new JSONObject();
			json.put("result",remoteCall.send());
			return json;
		}
}
