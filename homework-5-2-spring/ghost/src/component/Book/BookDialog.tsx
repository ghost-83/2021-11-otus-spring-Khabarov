import {Dispatch, FC, SetStateAction} from "react";
import {AppBar, Dialog, IconButton, Toolbar, Typography} from "@mui/material";
import {GhostIcon} from "../../icon/GhostIcon";
import {Close} from "@mui/icons-material";
import {modalSlice, ModalType} from "../../hook/slice/modal.slice";
import {useDispatch} from "react-redux";
import {IBook} from "../../model/IBook";
import {useAppSelector} from "../../hook/redux";

interface Props {
    data: IBook | null
    statusModal: boolean
    setPost: Dispatch<SetStateAction<IBook | null>>
}

export const BookDialog: FC<Props> = ({data, statusModal, setPost}) => {

    const {userAuth} = useAppSelector(state => state.authReducer)
    const dispatch = useDispatch()

    const handleOpen = () => {
        dispatch(modalSlice.actions.openModalWindow(ModalType.UPDATE))
        setPost(data)
    }

    const handleClose = () => {
        dispatch(modalSlice.actions.closeModalWindow())
    };

    return (
        <Dialog
            fullScreen
            open={statusModal}
            onClose={handleClose}
        >
            <AppBar sx={{position: 'relative'}}>
                <Toolbar>
                    <Typography variant="h6" noWrap component="div" sx={{flexGrow: 1}}>
                        <GhostIcon fontSize="large"/>
                        GHOST
                    </Typography>
                    <IconButton
                        edge="start"
                        color="inherit"
                        onClick={handleClose}
                        aria-label="close"
                    >
                        <Close/>
                    </IconButton>
                </Toolbar>
            </AppBar>
            <Typography sx={{color: 'text.secondary', width: '30%'}}>
                {JSON.stringify(data, null, 4)}
            </Typography>
        </Dialog>
    )
}