# DATA MODEL

1. Hem optat per en aquest Sprint fer un model de DB amb Room idèntic al fet a classe. Això degut a que la nostra idea al principi era persistir la classe 
HumanPlayer , i per consequència Player per a poder tenir un registre dels jugadors, de quins punts havien fet i a quin nivell havien quedat per a tal de 
en partides futures poder-ho recuperar. Per problemes tècnics de caràcter major com el gradle deixant de funcionar, o diferentes versions i schemas de Room
existint alhora ens han tirat enrrere en l'ambiciosa idea de persistir HumanPlayer.

2. El que persisteix donç es l'objecte Game, amb una clau primària "ID" autogenerada progresivament per cada objecte guardat, i amb ell el nombre màxim de 
punts fets aquella partida i el nombre de cartes que han necessitat per a aconseguir-ho

3. Link al diagrama en questió de la nostra única taula de moment: https://drive.google.com/file/d/1JbpvIixGVe6FCDvj1e504ljlXHf6nnwZ/view?usp=share_link

4. Link també al que tenim pensat sirà el de Player : https://drive.google.com/file/d/14MRALTPeV0lQziXNBDDG5PZS8HcyH-3G/view?usp=share_link
