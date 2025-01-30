const navbar={
    data (){
        return{
        }
    },
    mounted(){ // o que tiver dentro aqui, vai ser executado quando carregar a pagina
   
    },
    methods:{
    },
    template:
    `<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
    <span class="navbar-text">Navbar text</span>
      <div class=" collapse navbar-collapse" id="collapsibleNavbar">
        <ul  class="nav" >
          <li class="nav-item">
            <a class="nav-link" href="./ger_tipos.html">Gerenciamento de Tipos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>`
}
Vue.createApp(navbar).mount('#navbar')