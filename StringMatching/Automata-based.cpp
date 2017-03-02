#include <iostream>
#include <vector>
#include <map>
#include <string>
using namespace std;
inline int min(int x1, int x2){
    if(x1<x2){
        return x1;
    }
    return x2;
}

bool Matching_Prefix(const char * P, int k, int q, char a){
    if(k == 0){
        return true;
    }
    if(k==1){
        return P[0] == a;
    }
    return P[k-1] == a && (strncmp(P, P+q-k+1, k-1) == 0);

}

vector<map<char, int>> Compute_Transition_Function(const char * P, const char* input_character){
    int m = strlen(P);
    int j = 0, k = 0;
    cout<<"The length of Pattern is " << m << endl;
    vector<map<char,int>> transition_map(m+1);
    for(int q = 0; q < m; q++){
        cout << "P: " << q << endl;
        for(int j = 0; j < strlen(input_character); j++){
            k = min(m+1, q+2);
            do{
                k = k-1;
            }while(!Matching_Prefix(P, k, q, input_character[j]));
            transition_map[q][input_character[j]] = k;
            cout << "state P: " << q << ", input character is: " << input_character[j] << ", k: " << k << endl;
        }
    }
    return transition_map;
};

int Automaton_String_Match(char *T, char *P, vector<map<char,int>>transition_map){
    int n = strlen(T);
    int m = strlen(P);
    int q = 0;
    for(int i = 0; i <n; i++){
        q = transition_map[q][T[i]];
        if(q == m){
            return (i-m+1);
        }
    }
    return -1;
}

int main(){
    const char * input_character = "abcdefghijklmnopqrstuvwxyz";
    char T[] = "abaababacaba";
    char P[9] = "abaa";


    vector<map<char,int>> transition_map = Compute_Transition_Function(P, input_character);
    int shift = Automaton_String_Match(T, P, transition_map);
    cout << "Pattern occurs at shift " << shift << endl;
    return 0;

}
