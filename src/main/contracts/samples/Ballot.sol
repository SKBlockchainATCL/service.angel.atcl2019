pragma solidity >=0.4.22 <0.7.0;
//https://solidity.readthedocs.io/en/develop/solidity-by-example.html

contract Ballot {
    
    
    struct Proposal {
        bytes32 name;
        uint voteCount;
    }

    Proposal[] public proposals;

    address public chairpersion;

    
    struct Voter {
        uint weight;
        bool boted;
        address delegate;
        uint vote;
    }
    
    mapping(address => Voter) public voters;
    
    constructor(bytes32[] memory proposalNames) public {
        
        chairpersion = msg.sender;
        
        for(uint i = 0; i < proposalNames.length; i++){
            
            proposals.push(Proposal({ name: proposalNames[i], voteCount: 0}));
        }
        
    }
    
    function giveRightToVote(address voter) public{
        
        
        voters[voter].weight = 1;
    }
    
}