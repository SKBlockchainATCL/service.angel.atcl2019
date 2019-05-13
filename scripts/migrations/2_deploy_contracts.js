const MetaCoin = artifacts.require("MetaCoin");
const Ballot = artifacts.require("Ballot");

module.exports = function(deployer) {
  deployer.deploy(MetaCoin);

  var proposals=[web3.utils.asciiToHex('Apple', 32),
    web3.utils.fromAscii('Banana'),
    web3.utils.fromAscii('Orange')];
  //deployer.deploy(Ballot, ['Apple', 'Banana', 'Orange']);
  deployer.deploy(Ballot, proposals, {gas : 500000});
};