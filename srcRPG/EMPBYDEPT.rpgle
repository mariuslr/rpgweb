      * Sample tables created with
      * CALL QSYS.CREATE_SQL_SAMPLE ('SAMPLE')
      * See:
      * https://www.ibm.com/support/knowledgecenter/en/ssw_ibm_i_74/sqlp/rbafysamptblx.htm
      * We use here the EMPLOYEE Table:
      * https://www.ibm.com/support/knowledgecenter/en/ssw_ibm_i_74/sqlp/rbafyemployee.htm
      * ------------------------------------------------------------------
     FXEMP2     if   e           k disk
     D toFind          S              3
     D msg             S             52    inz(*blanks)
      *
     C     *entry        plist
     C                   parm                    toFind
      *-------------------------------------------------------------------------
     C                   eval      msg = '##COLUMNS'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C                   eval      msg = '#FIRSTNME'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C                   eval      msg = 'First name'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C                   eval      msg = '#LASTNAME'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C                   eval      msg = 'Last name'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C                   eval      msg = '##ROWS'
     C     msg           dsply
      *-------------------------------------------------------------------------
     C     toFind        ReadE     XEMP2
     C                   dow       not %eof
     C                   eval      msg = %trim(FIRSTNME)
     C     msg           dsply
     C                   eval      msg = %trim(LASTNAME)
     C     msg           dsply
     C                   eval      msg = '###'
     C     msg           dsply
     C                   ReadE     XEMP2
     C                   enddo
      * Closing resources.
     C                   seton                                        lr
