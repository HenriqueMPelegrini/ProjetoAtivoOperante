const home={
    data (){
        return{
            denuncias:[{"id":0,"titulo":"","texto":"","urgencia":"","data":"","orgao":"","tipo":"","usuario":"","feedback":""}],
            feedback:"",
            id:"",
            denuncia_aux:""
        }
    },
    mounted(){ // o que tiver dentro aqui, vai ser executado quando carregar a pagina
        this.carregarDenuncias();
    },
    methods:{
        carregarDenuncias(){
            let endpoint ="http://localhost:8080/apis/admin/get_denuncias" // esse endponit é o caminho lá no gatmapping no back
            axios.get(endpoint)
            .then(response => {this.denuncias = response.data; 
                console.log(this.denuncias)
            }) // é o retorno que da chamada da api
        },
        apagar(id){
            let endpoint ="http://localhost:8080/apis/admin/del_denuncia/"+id
            axios.get(endpoint)
            .then(response => {
             this.carregarDenuncias()
            })
        },
        buscar(id){
            let endpoint ="http://localhost:8080/apis/admin/get_denuncia/"+id;
            axios.get(endpoint)
            .then(response => {
                this.denuncia_aux = response.data;
                this.feedback = this.denuncia_aux.feedback.texto;
                this.id = this.denuncia_aux.id;
            })
        },
        cadastrarFeedback(){
            let object = {
                "texto":this.feedback
            } 
            //var json  = JSON.stringify(object)
            this.denuncia_aux.feedback =object;
            let endpoint = "http://localhost:8080/apis/admin/add_feedback_denuncia";
            axios.post(endpoint,this.denuncia_aux)
            .then(response => {
                
                this.carregarDenuncias()
            })
        }
    },
    template:
        ` <div class="container" style="padding-top: 5%;">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                <h4 class="card-title">Tipos de Problemas</h4>
                <p class="card-text">Click no botão abaixo para gerenciar os tipos de problemas.</p>
                <button type="button" class="btn btn-primary"> <a href="ger_tipos.html" style="color: white;" >Gerenciar Tipos de Problemas</a></button>
                </div>
          </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                <h4 class="card-title">Orgãos Responsáveis</h4>
                <p class="card-text">Click no botão abaixo para gerenciar os orgãos responsáveis.</p>
                <button type="button" class="btn btn-primary"> <a href="ger_orgaos.html" style="color: white;" >Gerenciar Orgãos Responsáveis</a></button>
                </div>
          </div>
        </div>
      </div>

</div>
<div class="container mt-5">
    <h2>Tabelas de Denuncias</h2>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Titulo</th>
          <th>Texto</th>
          <th>Urgencia</th>
          <th>Data</th>
          <th>Orgão Responsavel</th>
          <th>Tipo Problemas</th>
          <th>Usuario</th>
          <th>Feedback</th>
          <th>Excluir</th>
          <th>Cadastrar</th>

        </tr>
      </thead>
      <tbody>
            <tr v-for="d in denuncias">
                <td>{{d.titulo}}</td>
                <td>{{d.texto}}</td>
                <td>{{d.urgencia}}</td>
                <td>{{d.data}}</td>
                <td>{{d.orgao.nome}}</td>
                <td>{{d.tipo.nome}}</td>
                <td>{{d.usuario.email}}</td>
                <td v-if="d.feedback != null">{{d.feedback.texto}}</td>
                <td v-if="d.feedback == null">Sem Feedback</td>
                <td>
                    <i @click="apagar(d.id)" class="fa-solid fa-trash fa-2xl" />
                </td>
                <td>
                    <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#myModal" @click="buscar(d.id)">
                        <i class="fa-solid fa-floppy-disk fa-2xl"  ></i>
                    </button>
                </td>
            </tr>
      </tbody>
    </table>
  </div> 
  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
  
        <!-- Modal body -->
        <div class="modal-body">
        <div class="mb-3 mt-3">
            <label for="id">Id:</label>
            <input type="text" v-model="id" disabled class="form-control" id="id" placeholder="ID" name="id">  <!-- o v-model é para colocar o valor da variavel dentro do campo -->
        </div>
        <div class="mb-3">
            <label for="feedback">Feedback:</label>
            <input type="text"  v-model="feedback" class="form-control" id="feedback" placeholder="Digite o Feedback" name="feedback">
        </div>
        </div>
  
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" @click="cadastrarFeedback()"  data-bs-dismiss="modal">Cadastrar Feedback</button>
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
        </div>
  
      </div>
    </div>
  </div>
  
  `
}
Vue.createApp(home).mount('#home')

{/* <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" @click="buscar(d.id)">
                Cadastrar Feedback
               </button> */}