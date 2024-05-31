import M from 'materialize-css'

const createToast = (message) => {
    M.toast({html: message, classes: 'rounded'});
}

export default createToast;