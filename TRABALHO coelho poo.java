// Classe Quarto
class Quarto {
    constructor(id, nome) {
        this.id = id;
        this.nome = nome;
        this.disponivel = true;
    }

    // Marcar o quarto como indisponível
    marcarComoIndisponivel() {
        this.disponivel = false;
    }

    // Marcar o quarto como disponível
    marcarComoDisponivel() {
        this.disponivel = true;
    }
}

// Classe Hospede
class Hospede {
    constructor(nome, quarto) {
        this.nome = nome;
        this.quarto = quarto;
    }
}

// Classe Hotel
class Hotel {
    constructor() {
        this.quartos = [];
        this.hospedes = [];
    }

    // Adicionar um quarto ao hotel
    adicionarQuarto(quarto) {
        this.quartos.push(quarto);
    }

    // Adicionar um hóspede ao hotel
    adicionarHospede(hospede) {
        this.hospedes.push(hospede);
    }

    // Buscar um quarto pelo ID
    buscarQuartoPorId(id) {
        return this.quartos.find(quarto => quarto.id === id);
    }

    // Realizar o check-in
    fazerCheckIn(nomeHospede, idQuarto) {
        const quarto = this.buscarQuartoPorId(idQuarto);
        if (quarto && quarto.disponivel) {
            const hospede = new Hospede(nomeHospede, quarto);
            this.adicionarHospede(hospede);
            quarto.marcarComoIndisponivel();
            return true;
        }
        return false;
    }

    // Realizar o check-out
    fazerCheckOut(nomeHospede) {
        const hospede = this.hospedes.find(h => h.nome === nomeHospede);
        if (hospede) {
            const quarto = this.buscarQuartoPorId(hospede.quarto.id);
            quarto.marcarComoDisponivel();
            this.hospedes = this.hospedes.filter(h => h.nome !== nomeHospede);
            return true;
        }
        return false;
    }

    // Obter todos os quartos disponíveis
    obterQuartosDisponiveis() {
        return this.quartos.filter(quarto => quarto.disponivel);
    }

    // Obter todos os hóspedes no hotel
    obterHospedesNoHotel() {
        return this.hospedes;
    }
}

// Exemplo de uso
const hotel = new Hotel();

// Inicializar alguns quartos
hotel.adicionarQuarto(new Quarto(1, "Quarto 101"));
hotel.adicionarQuarto(new Quarto(2, "Quarto 102"));
hotel.adicionarQuarto(new Quarto(3, "Quarto 103"));
hotel.adicionarQuarto(new Quarto(4, "Quarto 104"));

// Realizar check-in
hotel.fazerCheckIn("João", 1);
hotel.fazerCheckIn("Maria", 2);

// Obter quartos disponíveis
console.log("Quartos disponíveis:", hotel.obterQuartosDisponiveis().map(quarto => quarto.nome));

// Obter hóspedes no hotel
console.log("Hóspedes no hotel:", hotel.obterHospedesNoHotel().map(hospede => ${hospede.nome} - ${hospede.quarto.nome}));

// Realizar check-out
hotel.fazerCheckOut("João");

// Obter quartos disponíveis após check-out
console.log("Quartos disponíveis após check-out:", hotel.obterQuartosDisponiveis().map(quarto => quarto.nome));