const orgao={
    data (){
        return{
            titulo:"Gerenciamento de Orgãos",
            orgaos:[{"id":0,"nome":""}],
            id:"",
            nome:"",
            orgaos_aux:""
        }
    },
    mounted(){ // o que tiver dentro aqui, vai ser executado quando carregar a pagina
      this.carregarOrgaos();
    },
    methods:{
        confirmar(){
           
            if (this.id ==="") {
              //GRAVAR
             let object ={} 
             let formData = new FormData(document.forms[0]);
             formData.forEach(function (value,key) { // convertendo os valores do form para json, montando um objeto
              object[key] = value
             })
             object.id =0;
             var json  = JSON.stringify(object)
             let endpoint ="http://localhost:8080/apis/admin/add_orgaos"
              axios.post(endpoint,JSON.parse(json))
               .then(response =>{
                console.log(response.data)
                  this.id=""
                  this.nome=""
                  this.carregarOrgaos();
              })
              .catch(error=>{ console.log(error)})
              
            } else {
              // ALTERAÇÂO
              this.orgaos_aux.nome = this.nome;// pois é o  nome unico que pode ser alterado, no caso
              let endpoint ="http://localhost:8080/apis/admin/upd_orgao"
             // console.log(this.tipo_aux);
              axios.post(endpoint,this.orgaos_aux)
               .then(response =>{
                console.log(response.data)
                  this.id=""
                  this.nome=""
                  this.carregarOrgaos();
              })
              .catch(error=>{ console.log(error)})
            }
           
            
        },
        carregarOrgaos(){
            let endpoint ="http://localhost:8080/apis/admin/get_orgaos" // esse endponit é o caminho lá no gatmapping no back
            axios.get(endpoint)
            .then(response => {this.orgaos =response.data; // tem que usar this, se não estara crianda uma variavel aqui
                //console.log(this.tipos)
            }) // é o retorno que da chamada da api
        },
        alterar(id){
          let endpoint ="http://localhost:8080/apis/admin/get_orgao/"+id
            axios.get(endpoint)
            .then(response => {
              this.orgaos_aux =response.data; 
                this.id=this.orgaos_aux.id;
                this.nome=this.orgaos_aux.nome;
            }) 
         
        },
        apagar(id){
          let endpoint ="http://localhost:8080/apis/admin/del_orgao/"+id
          axios.get(endpoint)
          .then(response => {
           this.carregarOrgaos()
          })
        }
    },
    template:
    ` <h2>{{titulo}}</h2>
    <form id="formtipo">
      <div class="mb-3 mt-3">
        <label for="id">Id:</label>
        <input type="text" v-model="id" disabled class="form-control" id="id" placeholder="ID" name="id">  <!-- o v-model é para colocar o valor da variavel dentro do campo -->
      </div>
      <div class="mb-3">
        <label for="nome">Nome:</label>
        <input type="text"  v-model="nome" class="form-control" id="nome" placeholder="Digite o Orgão" name="nome">
      </div>
     
      
    </form>
    <button @click="confirmar()"  class="btn btn-primary">Confirmar</button>
    <button  class="btn btn-primary">Cancelar</button>
    <hr>
    <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>NOME</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="t in orgaos">
            <td>{{t.id}}</td>
            <td>{{t.nome}}</td>
            <td><i  @click="apagar(t.id)" class="fa-solid fa-trash"></i></td>
            <td><i @click="alterar(t.id)" class="fa-solid fa-pen"></i></td>
          </tr>
        
        </tbody>
    </table>`
}
Vue.createApp(orgao).mount('#orgao')
